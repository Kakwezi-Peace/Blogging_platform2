package com.example.Blogging_platform2.repository;

import com.example.Blogging_platform2.model.PostView;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostViewRepository {

    private final JdbcTemplate jdbc;

    public PostViewRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<PostView> findByPostId(int postId) {
        return jdbc.query("SELECT * FROM post_views WHERE post_id = ?",
                new BeanPropertyRowMapper<>(PostView.class), postId);
    }

    public PostView findById(int id) {
        List<PostView> views = jdbc.query("SELECT * FROM post_views WHERE id = ?",
                new BeanPropertyRowMapper<>(PostView.class), id);
        return views.isEmpty() ? null : views.get(0);
    }

    public PostView save(PostView view) {
        jdbc.update("INSERT INTO post_views (post_id, user_id, viewed_at) VALUES (?, ?, ?)",
                view.getPostId(), view.getUserId(), view.getViewedAt());
        return view;
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM post_views WHERE id = ?", id);
    }
}
