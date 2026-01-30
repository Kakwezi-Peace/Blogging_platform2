package com.example.Blogging_platform2.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    // fields
    private int commentId;
    private int postId;
    private int userId;
    @NotBlank(message = "Comment content cannot be empty")
    private String content;
    private LocalDateTime createdAt;

}







