package com.demo.user.service.impl;

import com.demo.user.config.properties.JsonplaceholderProperties;
import com.demo.user.exception.CustomException;
import com.demo.user.model.User;
import com.demo.user.service.IUserService;
import com.google.common.collect.Lists;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ = { @Autowired })
public class UserServiceImpl implements IUserService {

    private JsonplaceholderProperties jsonplaceholderProperties;

    private RestTemplate restTemplate;

    @Override
    public List<User> getUsers() throws CustomException {
        String userUrl = jsonplaceholderProperties.getListUserUrl();
        User[] users = restTemplate.getForObject(userUrl,User[].class);
        return Lists.newArrayList(users);
    }
}
