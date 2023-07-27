package com.demo.user.model;

import lombok.Data;

@Data
public class PostModel {

    Long id;

    Long userId;

    String title;

    String body;

}
