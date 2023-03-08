package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 검수 요청
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CnrReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cnrReqId; // 검수 요청 ID
    private LocalDateTime dadDet; // 직접광고 상세 ID
    private String cnrIngStatus; // 검수 진행 상태
    private String cnrInputDiv; // 검수 입력 구분
    private LocalDateTime cnrReqTime; // 검수 요청 시간
    private LocalDateTime cnrProcTime; // 검수 처리 시간
    private Integer cnrCompleteYn; // 검수 완료 여부
    private String cnrFailCause; // 검수 실패 사유
    private String cnrFailComt; // 검수 실패 코멘트
}
