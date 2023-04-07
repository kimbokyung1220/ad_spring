package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskReq {
    @Id
    @Column(name = "task_req_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskReqId; // 테스크 요청 ID

    @Column(name = "basic_date")
    private LocalDateTime basicDate; // 기준 날짜

    @ManyToOne(fetch = FetchType.LAZY) // 직접광고 상세 ID
    @JoinColumn(name = "member_id")
    private Member member; // 회원 ID

    @Column(name = "task_status")
    private String taskStatus; // 테스크 상태 [요청:REQ / 진행 :ING / 완료:COMPLETE / 에러:ERR ]
    @Column(name = "task_name")
    private String taskName; // 테스크 명
    @Column(name = "task_req_file_path")
    private String taskReqFilePath; // 테스크 요청 파일 경로
    @Column(name = "task_req_time")
    private LocalDateTime taskReqTime; // 테스크 요청 시간
    @Column(name = "task_start_time")
    private LocalDateTime taskStartTime; // 테스크 시작 시간
    @Column(name = "task_end_time")
    private LocalDateTime taskEndTime; // 테스크 종료 시간

    public void updateTaskStatusAndStartTime(String status) {
        this.taskStatus = status;
        this.taskStartTime = LocalDateTime.now();
    }
}
