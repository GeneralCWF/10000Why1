package com.why10000.provider.user;

import com.why10000.common.result.R;
import com.why10000.common.util.ResultUtil;
import com.why10000.domain.User;
import com.why10000.domain.UserRec;
import com.why10000.mapper.user.UserMapper;
import com.why10000.mapper.user.UserRecMapper;
import com.why10000.service.user.UserService;
import org.apache.curator.retry.RetryUntilElapsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceProvider implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRecMapper userRecMapper;

    @Override
    public R save(User user) {

        UserRec userRec = new UserRec();
        R r = new R();
        if(user.getUid() > 0) {

            if (userMapper.selectUid(user.getUid())>0){
                userMapper.insert(user);
                userRec.setRecuid(user.getId());
                userRec.setBerecuid(user.getUid());
                userRecMapper.insert(userRec);
                return ResultUtil.createResult(1);
            } else {
                r.setCode(0);
                r.setMsg("推荐人不存在");
                return r;
            }

        } else {
            r.setCode(0);
            r.setMsg("推荐人不存在");
            return r;
        }

    }

    @Override
    public R selectName(String name) {
        return ResultUtil.createResult(userMapper.selectByName(name) != null?1:0);
    }


}
