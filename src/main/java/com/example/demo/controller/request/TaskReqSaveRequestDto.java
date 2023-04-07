package com.example.demo.controller.request;

import com.example.demo.entity.Member;
import com.example.demo.entity.TaskReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskReqSaveRequestDto {

    @NotBlank(message = "작업명을 입력해주세요")
    private String taskName; // 테스크 명

    public TaskReq saveTaskReq(Member member, String saveFilePath) {
        return TaskReq.builder()
                .basicDate(LocalDateTime.now())
                .member(member)
                .taskStatus("REQ")
                .taskName(taskName)
                .taskReqFilePath(saveFilePath)
                .taskReqTime(LocalDateTime.now())
                .build();
    }
}
