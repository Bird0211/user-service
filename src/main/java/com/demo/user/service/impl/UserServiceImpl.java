package com.demo.user.service.impl;

import com.demo.user.config.properties.JsonplaceholderProperties;
import com.demo.user.model.User;
import com.demo.user.service.IUserService;
import com.demo.user.util.OkHttpUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Setter(onMethod_ = { @Autowired })
public class UserServiceImpl implements IUserService {

    private JsonplaceholderProperties jsonplaceholderProperties;

    @Override
    public List<User> getUsers() throws Exception {
        String userUrl = jsonplaceholderProperties.getListUserUrl();
        return OkHttpUtils.getList(userUrl, User.class);
    }
}
