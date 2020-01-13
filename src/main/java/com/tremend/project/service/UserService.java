package com.tremend.project.service;

import com.tremend.project.exceptions.UserNotFoundException;
import com.tremend.project.model.entity.UserEntity;
import com.tremend.project.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<UserEntity> getAllUsers() {
        List<UserEntity> result = repository.findAll();

        if (result.size()>0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public UserEntity getUserById(Long id) throws UserNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public UserEntity createOrUpdateUser(UserEntity userEntity)
    {
        if(userEntity.getId()  == null)
        {
            userEntity = repository.save(userEntity);

            return userEntity;
        }
        else
        {
            Optional<UserEntity> user = repository.findById(userEntity.getId());

            if(user.isPresent())
            {
                UserEntity newUser = user.get();
                newUser.setFirstName(userEntity.getFirstName());
                newUser.setLastName(userEntity.getLastName());
                newUser.setPrefTemp(userEntity.getPrefTemp());
                newUser.setOnTime(userEntity.getOnTime());
                newUser.setOffTime(userEntity.getOffTime());

                newUser = repository.save(newUser);

                return newUser;
            } else {
                userEntity = repository.save(userEntity);

                return userEntity;
            }
        }
    }

    public void deleteUserById(Long id) throws UserNotFoundException
    {
        Optional<UserEntity> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }
}
