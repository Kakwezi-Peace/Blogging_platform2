package com.example.Blogging_platform2.exception;




public class PostViewNotFoundException extends RuntimeException {
    public PostViewNotFoundException(int id) {
        super("PostView with ID " + id + " not found");
    }
}
