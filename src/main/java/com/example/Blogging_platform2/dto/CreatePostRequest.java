package com.example.Blogging_platform2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
    private Integer userId;
    private String title;
    private String content;

}



