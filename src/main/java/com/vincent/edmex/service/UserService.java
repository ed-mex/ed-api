package com.vincent.edmex.service;

import com.vincent.edmex.pojo.User;
import com.vincent.edmex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean userAlreadyExists(String username) {
        return Optional.ofNullable(getUserFromUsername(username)).isPresent();
    }

    public User getUserFromUsername(String username) {
        return userRepository.getUserFromUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Boolean addNewUser(User newUser) {
        return userRepository.addNewUser(newUser);
    }
}
