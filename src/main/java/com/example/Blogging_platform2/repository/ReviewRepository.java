package com.example.Blogging_platform2.repository;

import com.example.Blogging_platform2.model.Review;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    private final JdbcTemplate jdbc;

    public ReviewRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Review> findAllByPostId(int postId) {
        return jdbc.query("SELECT * FROM reviews WHERE post_id = ?",
                new BeanPropertyRowMapper<>(Review.class), postId);
    }

    public Review findById(int id) {
        List<Review> reviews = jdbc.query("SELECT * FROM reviews WHERE review_id = ?",
                new BeanPropertyRowMapper<>(Review.class), id);
        return reviews.isEmpty() ? null : reviews.get(0);
    }

    public Review save(Review review) {
        jdbc.update("INSERT INTO reviews (post_id, user_id, rating, comment, created_at) VALUES (?, ?, ?, ?, ?)",
                review.getPostId(), review.getUserId(), review.getRating(), review.getComment(), review.getCreatedAt());
        return review;
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM reviews WHERE review_id = ?", id);
    }
}
