package com.why10000.controller.user;

import com.why10000.common.result.R;
import com.why10000.domain.User;
import com.why10000.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/usersave.do")
    public R usersave(User user){
        return userService.save(user);
    }

    @GetMapping("/nameCheck.do")
    public R nameCheck(String username){
        R r = userService.selectName(username);
        if(r.getCode() == 1){
            r.setCode(1010);
        } else {
            r.setCode(0);
        }
        return r;
    }
}
