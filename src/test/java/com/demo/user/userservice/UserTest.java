package com.demo.user.userservice;

import com.demo.user.model.User;
import com.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
public class UserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IUserService userService;

    @Test
    void testUsersNotEmpty() {
        List<User> users = userService.getUsers();
        Assert.notEmpty(users,"Users is empty");
    }

}
