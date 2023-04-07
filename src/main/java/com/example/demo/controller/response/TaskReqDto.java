package com.example.demo.controller.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TaskReqDto {
    private String taskStatus;
    private LocalDateTime taskStartTime;
    @QueryProjection
    public TaskReqDto(String taskStatus, LocalDateTime taskStartTime) {
        this.taskStatus = taskStatus;
        this.taskStartTime = taskStartTime;
    }
// 광고 검수 대상 검색


}
