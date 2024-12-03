package com.api.backend.infrastructure.in.controller;

import com.api.backend.application.ports.in.UserService;
import com.api.backend.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping("GetAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @QueryMapping("GetUser")
    public User getUser(@Argument String userId){
        return userService.getUser(userId);
    }

    @MutationMapping("CreateUser")
    public User createUser(@Argument User user){
        return userService.createUser(user);
    }

    @MutationMapping("UpdateUser")
    public User updateUser(@Argument String userId,@Argument User user){
        return userService.updateUser(userId,user);
    }

    @MutationMapping("DeleteUser")
    public Boolean deleteUser(@Argument String userId){
        return userService.deleteUser(userId);
    }
}
