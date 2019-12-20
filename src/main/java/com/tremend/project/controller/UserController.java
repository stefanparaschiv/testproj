package com.tremend.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("new-user")
    public String newUser () {
        return "new-user";
    }

    @RequestMapping("/all-users")
    public String allUsers() {
        return "all-users";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
