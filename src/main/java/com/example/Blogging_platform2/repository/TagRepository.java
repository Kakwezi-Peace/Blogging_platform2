package com.example.Blogging_platform2.repository;

import com.example.Blogging_platform2.model.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {

    private final JdbcTemplate jdbc;

    public TagRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Tag> findAll() {
        return jdbc.query("SELECT * FROM tags", new BeanPropertyRowMapper<>(Tag.class));
    }

    public Tag findById(int id) {
        List<Tag> tags = jdbc.query("SELECT * FROM tags WHERE tag_id = ?",
                new BeanPropertyRowMapper<>(Tag.class), id);
        return tags.isEmpty() ? null : tags.get(0);
    }

    public Tag save(Tag tag) {
        jdbc.update("INSERT INTO tags (name) VALUES (?)", tag.getName());
        return tag;
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM tags WHERE tag_id = ?", id);
    }
}
