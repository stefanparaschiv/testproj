package com.tremend.project.entity;

import java.util.EmptyStackException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
