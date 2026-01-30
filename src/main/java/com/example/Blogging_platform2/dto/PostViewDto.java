package com.example.Blogging_platform2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostViewDto {

    private Integer id;
    @NotNull(message = "Post ID is required")
    private Integer postId;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "ViewedAt timestamp is required")
    private LocalDateTime viewedAt;
}
