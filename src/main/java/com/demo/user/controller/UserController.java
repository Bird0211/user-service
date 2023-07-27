package com.demo.user.controller;

import com.demo.user.aop.AopLog;
import com.demo.user.aop.BaseResponse;
import com.demo.user.model.User;
import com.demo.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
@RequestMapping("/users")
@Slf4j
@AopLog
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping()
    public List<User> getUsers() throws Exception {
        return userService.getUsers();
    }

}
