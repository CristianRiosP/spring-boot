package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.caseUse.getUser;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private getUser getUser;

    public UserRestController(getUser getUser) {
        this.getUser = getUser;
    }
   @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
}
}
