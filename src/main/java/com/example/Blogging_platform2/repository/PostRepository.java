package com.example.Blogging_platform2.repository;

import com.example.Blogging_platform2.model.Post;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbc;

    public PostRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Basic findAll
    public List<Post> findAll() {
        return jdbc.query("SELECT * FROM posts ORDER BY created_at DESC",
                new BeanPropertyRowMapper<>(Post.class));
    }

    // Paginated + sorted findAll
    public List<Post> findAll(int page, int size, String sort) {
        int offset = page * size;
        String sql = "SELECT * FROM posts ORDER BY " + sort + " LIMIT ? OFFSET ?";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Post.class), size, offset);
    }


    public Post findById(int id) {
        List<Post> posts = jdbc.query("SELECT * FROM posts WHERE id = ?",
                new BeanPropertyRowMapper<>(Post.class), id);
        return posts.isEmpty() ? null : posts.get(0);
    }

    public void save(Post post) {
        jdbc.update("INSERT INTO posts (user_id, title, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)",
                post.getUserId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdatedAt());
    }

    public void update(Post post) {
        jdbc.update("UPDATE posts SET title = ?, content = ?, updated_at = ? WHERE id = ?",
                post.getTitle(), post.getContent(), post.getUpdatedAt(), post.getId());
    }

    public void deleteById(int id) {
        jdbc.update("DELETE FROM posts WHERE id = ?", id);
    }
}
