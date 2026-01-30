package com.example.Blogging_platform2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLog {
    private int id;
    private Integer userId;
    private String action;
    private Integer targetId;
    private String details;
    private LocalDateTime timestamp;


    public ActivityLog(Integer userId, String action, Integer targetId, String details, LocalDateTime timestamp) {
        this.userId = userId;
        this.action = action;
        this.targetId = targetId;
        this.details = details;
        this.timestamp = timestamp;
    }
}

//