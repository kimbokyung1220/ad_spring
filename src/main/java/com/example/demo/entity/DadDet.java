package com.example.demo.entity;

import java.time.LocalDateTime;

/**
 * 직접광고 상세
 */
public class DadDet {
    private Long dadDetId; // 직접광고 상세 ID
    private Ad ad; // 광고 ID (FK)
    private Kwd kwd; // 키워드 ID (FK)
    private String dadCnrStatus; // 직접광고 검수 상태
    private CnrReq cnrReq; // 검수 요청 ID (FK)
    private Integer dadUseConfigYn; // 직접광고 사용 설정 여부
    private Integer dadActYn; // 직접광고 활성 여부
    private LocalDateTime regTime; // 등록시간
}
