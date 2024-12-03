package com.api.backend.application.services;

import com.api.backend.application.ports.in.UserService;
import com.api.backend.application.ports.out.UserServicePort;
import com.api.backend.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserServicePort userServicePort;
    @Autowired
    public UserServiceImpl(UserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Override
    public List<User> getAllUsers() {
        return userServicePort.getAllUsers();
    }

    @Override
    public User getUser(String userId) {
        return userServicePort.getUser(userId);
    }

    @Override
    public User createUser(User user) {
        return userServicePort.createUser(user);
    }

    @Override
    public User updateUser(String userId, User user) {
        return userServicePort.updateUser(userId,user);
    }

    @Override
    public Boolean deleteUser(String userId) {
        return userServicePort.deleteUser(userId);
    }
}
