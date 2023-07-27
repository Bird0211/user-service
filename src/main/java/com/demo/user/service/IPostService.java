package com.demo.user.service;

import com.demo.user.model.PostModel;

import java.util.List;

public interface IPostService {

    List<PostModel> getPostByUser(Long userId) throws Exception;

}
