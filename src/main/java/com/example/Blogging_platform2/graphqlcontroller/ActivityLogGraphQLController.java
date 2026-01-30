package com.example.Blogging_platform2.graphqlcontroller;

import com.example.Blogging_platform2.model.ActivityLog;
import com.example.Blogging_platform2.service.ActivityLogService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ActivityLogGraphQLController {

    private final ActivityLogService service;

    public ActivityLogGraphQLController(ActivityLogService service) {
        this.service = service;
    }

    @QueryMapping
    public List<ActivityLog> getAllLogs() {
        return service.getAllLogs();
    }

    @QueryMapping
    public ActivityLog getLog(@Argument int id) {
        return service.getLog(id);
    }

    @MutationMapping
    public ActivityLog createLog(@Argument Integer userId,
                                 @Argument String action,
                                 @Argument Integer targetId,
                                 @Argument String details) {
        ActivityLog log = new ActivityLog();
        log.setUserId(userId);
        log.setAction(action);
        log.setTargetId(targetId);
        log.setDetails(details);
        return service.createLog(log);
    }

    @MutationMapping
    public boolean deleteLog(@Argument int id) {
        return service.deleteLog(id);
    }
}
