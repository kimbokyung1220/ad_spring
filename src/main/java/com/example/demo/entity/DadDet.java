package com.example.demo.entity;

import java.util.Date;

/**
 * 직접광고 상세
 */
public class DadDet {
    private Long dadDetId; // 직접광고 상세 ID
    private Ad ad; // 광고 ID (FK)
    private Kwd kwd; // 키워드 ID (FK)
    private String dadCnrStatus; // 직접광고 검수 상태
    private cnrReq cnrReq; // 검수 요청 ID (FK)
    private int dadUseConfigYn; // 직접광고 사용 설정 여부
    private int dadActYn; // 직접광고 활성 여부
    private Date regTime; // 등록시간
}
