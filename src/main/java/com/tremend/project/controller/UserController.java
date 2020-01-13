package com.tremend.project.controller;

import com.tremend.project.model.entity.UserEntity;
import com.tremend.project.exceptions.UserNotFoundException;
import com.tremend.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/users/list")
    public String getAllUsers(Model model) {
        List<UserEntity> list = service.getAllUsers();
        model.addAttribute("users",list);
        return "all-users";
    }

    @RequestMapping(path = {"/users/add","users/add/{id}"})
        public String editUser(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            UserEntity userEntity = service.getUserById(id.get());
            model.addAttribute("user", userEntity);
        } else {
            model.addAttribute("user", new UserEntity());
        }
        return "new-user";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id)
            throws UserNotFoundException {
            service.deleteUserById(id);
        return "redirect:/users/list";
    }


    @RequestMapping(path = "/users/add", method = RequestMethod.POST)
    public String addOrUpdateUser(UserEntity userEntity) { service.createOrUpdateUser(userEntity);
    return "redirect:/users/list";
    }

    @RequestMapping("/users/delete/all")
    public @ResponseBody void deleteAllUsers(){ service.deleteAllUsers();
    }

    @RequestMapping("api/users/all")
    public @ResponseBody List<UserEntity> getAllUsers() {
        return service.getAllUsers();
    }

    @RequestMapping("api/users/add")
    public @ResponseBody UserEntity addOrUpdateUsers(@Valid @RequestBody UserEntity userEntity) {
        return service.createOrUpdateUser(userEntity);
    }
}
