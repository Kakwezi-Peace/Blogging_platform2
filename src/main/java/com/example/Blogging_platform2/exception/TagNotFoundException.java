package com.example.Blogging_platform2.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(int id) {
        super("Tag with ID " + id + " not found");
    }
}
