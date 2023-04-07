package com.example.demo.repository;

import com.example.demo.controller.response.TaskReqResponseDto;
import com.example.demo.entity.TaskReq;
import com.example.demo.entity.enm.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskReqRepository extends JpaRepository<TaskReq, Long> {
    List<TaskReq> findAllByOrderByTaskReqTime();
    List<TaskReq> findByTaskStatus(String taskStatus);
//    Optional<TaskReq> findByTaskStatus(String taskStatus);

    TaskReq findByTaskReqId(Long taskReqId);
    TaskReq findByTaskReqFilePath(String filePath);

    @Modifying(clearAutomatically = true)
    @Query("update TaskReq t set t.taskStatus = :taskStatus, t.taskStartTime = :taskStartTime where t.taskReqId = :taskReqId")
    @Transactional
    void updateTaskStatusAndStartTime(@Param("taskStatus")String taskStatus, @Param("taskStartTime")LocalDateTime taskStartTime, @Param("taskReqId")Long taskReqId);

    @Modifying(clearAutomatically = true)
    @Query("update TaskReq t set t.taskStatus = :taskStatus where t.taskReqId = :taskReqId")
    @Transactional
    void updateTaskStatus(@Param("taskStatus")String taskStatus,@Param("taskReqId")Long taskReqId);

}
