package com.fundamentos.springboot.fundamentos.caseUse;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;

import java.util.List;

public class getUserImplement implements getUser{

    private UserService userService;

    public getUserImplement(UserService userService) {
        this.userService = userService;
    }


    @Override
    public List<User> getAll() {
        return userService.getListUsers();
    }
}
