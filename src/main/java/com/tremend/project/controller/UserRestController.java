package com.tremend.project.controller;

import com.tremend.project.entity.UserEntity;
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
    public List<UserEntity> all() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public UserEntity one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users/{id}")
    public UserEntity newUser (@RequestBody UserEntity newUserEntity) {
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

}
