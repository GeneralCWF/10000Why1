package com.why10000.provider.sso;


import com.alibaba.fastjson.JSON;
import com.why10000.common.redis.JedisUtil;
import com.why10000.common.result.R;
import com.why10000.common.util.EncrypUtil;
import com.why10000.domain.User;
import com.why10000.mapper.user.UserMapper;
import com.why10000.service.sso.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service("ssoServiceProvider")
public class SSOServiceProvider implements SSOService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R login(String name, String pass) {

        User user = userMapper.selectByName(name);
        System.out.println(user);
        if(user != null) {
            if(Objects.equals(pass,user.getPassword())){
                String token = EncrypUtil.md5Pass(user.getId().toString(),name);

                jedisUtil.addStr(token,JSON.toJSONString(user),TimeUnit.MINUTES,30);

                return new R(1001,"登录成功",token);
            } else {
                return new R(1003,"密码错误");
            }
        } else {
            return new R(1002,"用户名不存在");
        }
    }

    @Override
    public R loginOut(String token) {
        jedisUtil.delKey(token);
        return R.setOK();
    }

    @Override
    public R loginCheck(String token) {
        String json = jedisUtil.getStr(token);
        jedisUtil.expire(token,TimeUnit.MINUTES,30);
        if(jedisUtil.isKey(token)){
            return new R(1001,"已登录",JSON.parseObject(json,User.class));
        }

        return R.setERROR();
    }


}
