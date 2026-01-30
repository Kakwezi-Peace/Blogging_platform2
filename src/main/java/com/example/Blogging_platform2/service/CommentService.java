package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.exception.CommentNotFoundException;
import com.example.Blogging_platform2.model.Comment;
import com.example.Blogging_platform2.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repo;

    public CommentService(CommentRepository repo) {
        this.repo = repo;
    }

    public List<Comment> getCommentsByPost(int postId) {
        return repo.findAllByPostId(postId);
    }

    public Comment getComment(int id) {
        Comment comment = repo.findById(id);
        if (comment == null) {
            throw new CommentNotFoundException(id);
        }
        return comment;
    }


    public Comment createComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return repo.save(comment);
    }

    public boolean deleteComment(int id) {
        return repo.delete(id) > 0;
    }
}
