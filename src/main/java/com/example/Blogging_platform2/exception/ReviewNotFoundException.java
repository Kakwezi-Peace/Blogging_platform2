package com.example.Blogging_platform2.exception;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(int id) {
        super("Review with ID " + id + " not found");
    }
}
