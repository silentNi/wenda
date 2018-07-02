package com.newcoder.wenda.controller;

import com.newcoder.wenda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/7/2.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password,
                        @RequestParam(value = "rememberme",defaultValue = "false") boolean rememberme){
        if(!userService.userJudge(username,password)){ //密码错误
            return "login";
        }
        return "index";
    }
}
