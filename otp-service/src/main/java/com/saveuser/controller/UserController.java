package com.saveuser.controller;

import com.saveuser.entity.User;
import com.saveuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/{mobileNo}")
    public ResponseEntity<User> createUser(@PathVariable(value = "mobileNo") String mobileNo) {
        return ResponseEntity.ok().body(userService.saveUser(mobileNo));
    }
}
