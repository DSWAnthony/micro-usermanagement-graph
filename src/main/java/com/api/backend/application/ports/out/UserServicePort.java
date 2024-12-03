package com.api.backend.application.ports.out;

import com.api.backend.domain.model.User;

import java.util.List;

public interface UserServicePort {
    List<User> getAllUsers();
    User getUser(String userId);
    User createUser(User user);
    User updateUser(String userId, User user);
    Boolean deleteUser(String userId);
}
