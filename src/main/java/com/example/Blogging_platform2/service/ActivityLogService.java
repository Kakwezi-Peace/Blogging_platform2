package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.exception.ActivityLogNotFoundException;
import com.example.Blogging_platform2.model.ActivityLog;
import com.example.Blogging_platform2.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository repo;

    public ActivityLogService(ActivityLogRepository repo) {
        this.repo = repo;
    }

    public List<ActivityLog> getAllLogs() {
        return repo.findAll();
    }

    public ActivityLog getLog(int id) {
        ActivityLog log = repo.findById(id);
        if (log == null) {
            throw new ActivityLogNotFoundException(id);
        }
        return log;
    }


    public ActivityLog createLog(ActivityLog log) {
        log.setTimestamp(LocalDateTime.now());
        return repo.save(log);
    }

    public boolean deleteLog(int id) {
        return repo.delete(id) > 0;
    }
}
