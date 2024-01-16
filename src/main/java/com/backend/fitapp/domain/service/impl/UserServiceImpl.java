package com.backend.fitapp.domain.service.impl;

import com.backend.fitapp.domain.model.User;
import com.backend.fitapp.domain.repository.UserRepository;
import com.backend.fitapp.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User newUser = null;
        if(!userRepository.existsByEmail(user.getEmail()) && !userRepository.existsByUsername(user.getUsername())){
            userRepository.saveAndFlush(user);
            newUser = userRepository.findByEmail(user.getEmail());

        }
        return newUser;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
