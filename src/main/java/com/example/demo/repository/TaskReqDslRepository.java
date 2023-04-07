package com.example.demo.repository;

import java.time.LocalDateTime;

public interface TaskReqDslRepository {
    void updateTaskStatusAndStartTime(String status, LocalDateTime taskStartTime);
}
