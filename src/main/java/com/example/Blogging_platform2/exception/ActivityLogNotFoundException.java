package com.example.Blogging_platform2.exception;

public class ActivityLogNotFoundException extends RuntimeException {
    public ActivityLogNotFoundException(int id) {
        super("ActivityLog with ID " + id + " not found");
    }
}
//