package com.backend.fitapp.domain.service;

import com.backend.fitapp.domain.model.User;

public interface UserService {
    User saveUser(User user);
    User findUserByUsername(String username);
}
