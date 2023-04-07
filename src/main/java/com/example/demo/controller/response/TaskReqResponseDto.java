package com.example.demo.controller.response;

import com.example.demo.entity.Member;
import com.example.demo.entity.TaskReq;
import com.example.demo.util.FormatDateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskReqResponseDto {
    private Long taskReqId; // 테스크 요청 ID
    private String memberId; // 회원 ID
    private String taskStatus; // 테스크 상태 [요청:REQ / 진행 :ING / 완료:COMPLETE / 에러:ERR ]
    private String taskName; // 테스크 명
    private String taskReqFilePath; // 테스크 요청 파일 경로
    private String taskReqTime; // 테스크 요청 시간

    public static TaskReqResponseDto of(TaskReq taskReq) {
        return TaskReqResponseDto.builder()
                .taskReqId(taskReq.getTaskReqId())
                .memberId(taskReq.getMember().getMemberId())
                .taskStatus(taskReq.getTaskStatus())
                .taskName(taskReq.getTaskName())
                .taskReqFilePath(taskReq.getTaskReqFilePath())
                .taskReqTime(taskReq.getTaskReqTime().format(DateTimeFormatter.ofPattern(FormatDateUtil.fm)))
                .build();
    }
}