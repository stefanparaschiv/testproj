package com.tremend.project.controller;

import com.tremend.project.entity.User;
import com.tremend.project.entity.UserNotFoundException;
import com.tremend.project.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users/{id}")
    User newUser (@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @PutMapping("/users/{id}")
    User updateUser (@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id).map(user -> {
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setPrefTemp(user.getPrefTemp());
            user.setOnTime(user.getOnTime());
            user.setOffTime(user.getOffTime());
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser (@PathVariable Long id) {
        repository.deleteById(id);
    }

}
