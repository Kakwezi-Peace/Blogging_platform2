package com.example.Blogging_platform2.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(int id) {
        super("Comment with ID " + id + " not found");
    }
}
