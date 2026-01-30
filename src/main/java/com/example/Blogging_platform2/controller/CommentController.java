package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.CommentDto;
import com.example.Blogging_platform2.model.Comment;
import com.example.Blogging_platform2.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDto dto) {
        Comment comment = new Comment();
        comment.setPostId(dto.getPostId());
        comment.setUserId(dto.getUserId());
        comment.setContent(dto.getContent());
        return service.createComment(comment);
    }


    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable int postId) {
        return service.getCommentsByPost(postId);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable int id) {
        return service.getComment(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteComment(@PathVariable int id) {
        return service.deleteComment(id);
    }
}
