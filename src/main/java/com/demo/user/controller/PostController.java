package com.demo.user.controller;


import com.demo.user.aop.BaseResponse;
import com.demo.user.exception.CustomException;
import com.demo.user.model.PostModel;
import com.demo.user.service.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseResponse
@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {

    @Autowired
    IPostService postService;

    @GetMapping("/user/{userId}")
    List<PostModel> getPostByUser(@PathVariable Long userId) throws CustomException {

        return postService.getPostByUser(userId);
    }

}
