package com.demo.user.service.impl;

import com.demo.user.config.properties.JsonplaceholderProperties;
import com.demo.user.exception.CustomException;
import com.demo.user.model.PostModel;
import com.demo.user.service.IPostService;
import com.google.common.collect.Lists;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Setter(onMethod_ = { @Autowired})
public class PostServiceImpl implements IPostService {

    private JsonplaceholderProperties jsonplaceholderProperties;

    private RestTemplate restTemplate;

    @Override
    public List<PostModel> getPostByUser(Long userId) throws CustomException {

        String url = jsonplaceholderProperties.getUserPostUrl();
        Map<String, String> queryParameter = new HashMap<>();
        queryParameter.put("userId", Long.toString(userId));

        PostModel[] postModels = restTemplate.getForObject(url,PostModel[].class,queryParameter);
        return Lists.newArrayList(postModels);
    }


}
