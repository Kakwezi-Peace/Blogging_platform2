package com.example.Blogging_platform2.repository;

import com.example.Blogging_platform2.model.ActivityLog;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityLogRepository {

    private final JdbcTemplate jdbc;

    public ActivityLogRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Save/log an activity
    public ActivityLog save(ActivityLog log) {
        jdbc.update("INSERT INTO activity_logs (user_id, action, target_id, details, timestamp) VALUES (?, ?, ?, ?, ?)",
                log.getUserId(), log.getAction(), log.getTargetId(), log.getDetails(), log.getTimestamp());
        return log;
    }

    public List<ActivityLog> findAll() {
        return jdbc.query("SELECT * FROM activity_logs", new BeanPropertyRowMapper<>(ActivityLog.class));
    }

    public ActivityLog findById(int id) {
        List<ActivityLog> logs = jdbc.query("SELECT * FROM activity_logs WHERE id = ?",
                new BeanPropertyRowMapper<>(ActivityLog.class), id);
        return logs.isEmpty() ? null : logs.get(0);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM activity_logs WHERE id = ?", id);
    }
}
