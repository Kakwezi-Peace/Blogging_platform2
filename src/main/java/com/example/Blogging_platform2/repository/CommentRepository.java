package com.example.Blogging_platform2.repository;

import com.example.Blogging_platform2.model.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    private final JdbcTemplate jdbc;

    public CommentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Comment> findAllByPostId(int postId) {
        return jdbc.query("SELECT * FROM comments WHERE post_id = ?",
                new BeanPropertyRowMapper<>(Comment.class), postId);
    }

    public Comment findById(int id) {
        List<Comment> comments = jdbc.query("SELECT * FROM comments WHERE comment_id = ?",
                new BeanPropertyRowMapper<>(Comment.class), id);
        return comments.isEmpty() ? null : comments.get(0);
    }

    public Comment save(Comment comment) {
        jdbc.update("INSERT INTO comments (post_id, user_id, content, created_at) VALUES (?, ?, ?, ?)",
                comment.getPostId(), comment.getUserId(), comment.getContent(), comment.getCreatedAt());
        return comment;
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM comments WHERE comment_id = ?", id);
    }
}
