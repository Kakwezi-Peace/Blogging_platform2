package com.example.Blogging_platform2.exception;


public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(int id) {
        super("Post with ID " + id + " not found");
    }
}
