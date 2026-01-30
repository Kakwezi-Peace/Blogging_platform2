package com.example.Blogging_platform2.controller;

import com.example.Blogging_platform2.dto.ApiResponse;
import com.example.Blogging_platform2.model.ActivityLog;
import com.example.Blogging_platform2.service.ActivityLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class ActivityLogController {

    private final ActivityLogService service;

    public ActivityLogController(ActivityLogService service) {
        this.service = service;
    }

    @PostMapping
    public ActivityLog createLog(@RequestBody ApiResponse.ActivityLogDto dto) {
        ActivityLog log = new ActivityLog();
        log.setUserId(dto.getUserId());
        log.setAction(dto.getAction());
        log.setTargetId(dto.getTargetId());
        log.setDetails(dto.getDetails());
        return service.createLog(log);
    }

    @GetMapping
    public List<ActivityLog> getAllLogs() {
        return service.getAllLogs();
    }

    @GetMapping("/{id}")
    public ActivityLog getLog(@PathVariable int id) {
        return service.getLog(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteLog(@PathVariable int id) {
        return service.deleteLog(id);
    }
}
