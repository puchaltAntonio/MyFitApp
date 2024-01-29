package com.backend.fitapp.domain.user.service;

import com.backend.fitapp.domain.user.model.User;

public interface UserService {
    User saveUser(User user);
    User findUserByUsername(String username);
}
