package com.why10000.controller;

import com.why10000.common.result.R;
import com.why10000.common.util.CookieUtil;
import com.why10000.service.sso.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SSOController {

    @Autowired
    private SSOService ssoService;

    @RequestMapping("/ssologin.do")
    public R ssologin(HttpServletRequest request, String name, String password, HttpServletResponse response){
        String token = CookieUtil.getCk(request,"userauth");
        System.out.println("ssologin.do>>>name:"+name+">>>>" + password);
        System.out.println("ssologin.do>>>token:"+token);
       if(token == null || token.length() == 0){

           R r = ssoService.login(name,password);
           if(r.getCode() == 1001){
               CookieUtil.setCK(response,"userauth",r.getData().toString());
           }

           return r;
       } else {
           return ssoService.loginCheck(token);
       }
    }

    @RequestMapping("/ssologincheck.do")
    public R check(HttpServletRequest request,HttpServletResponse response){
        String token = CookieUtil.getCk(request,"userauth");
        System.out.println("ssologincheck>>>"+token);
        if(token == null){
            return R.setERROR();
        }
        return ssoService.loginCheck(token);
    }

    @RequestMapping("/ssologinout.do")
    public R loginout(HttpServletRequest request,HttpServletResponse response){

        String token = CookieUtil.getCk(request,"userauth");
        if(token == null){
            return R.setERROR();
        } else {
            CookieUtil.delCK(response,"userauth");
            return R.setOK();
        }

    }

}
