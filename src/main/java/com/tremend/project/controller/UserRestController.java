package com.tremend.project.controller;

import com.tremend.project.entity.UserEntity;
import com.tremend.project.entity.UserNotFoundException;
import com.tremend.project.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<UserEntity> all(Model model) {
        model.addAttribute("all-users", new UserEntity());
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public UserEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users/{id}")
    public UserEntity newUser (@RequestBody UserEntity newUserEntity, Model model) {
        model.addAttribute("UserEntity", new UserEntity());
        return repository.save(newUserEntity);
    }

    @PutMapping("/users/{id}")
    public UserEntity updateUser (@RequestBody UserEntity newUserEntity, @PathVariable Long id) {
        return repository.findById(id).map(userEntity -> {
            userEntity.setFirstName(userEntity.getFirstName());
            userEntity.setLastName(userEntity.getLastName());
            userEntity.setPrefTemp(userEntity.getPrefTemp());
            userEntity.setOnTime(userEntity.getOnTime());
            userEntity.setOffTime(userEntity.getOffTime());
            return repository.save(userEntity);
        }).orElseGet(() -> {
            newUserEntity.setId(id);
            return repository.save(newUserEntity);
        });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/users/all")
    public void deleteAllUsers () {
        repository.deleteAll();
    }


    @RequestMapping("new-user")
    public String newUser (Model model) {
        model.addAttribute("UserEntity", new UserEntity());
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
