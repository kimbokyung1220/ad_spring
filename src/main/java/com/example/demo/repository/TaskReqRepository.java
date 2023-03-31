package com.example.demo.repository;

import com.example.demo.entity.TaskReq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskReqRepository extends JpaRepository<TaskReq, Long> {
}
