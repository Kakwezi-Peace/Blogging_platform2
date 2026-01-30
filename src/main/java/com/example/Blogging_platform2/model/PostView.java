package com.example.Blogging_platform2.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostView {
    private int id;
    private int postId;
    private Integer userId;
    private LocalDateTime viewedAt;
}

