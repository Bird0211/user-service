package com.demo.user.service;

import com.demo.user.exception.CustomException;
import com.demo.user.model.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers() throws CustomException;

}
