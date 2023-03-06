package com.example.demo.entity;

import java.util.Date;

/**
 * 검수 요청
 */
public class CrnReq {
    private Long cnrReqId; // 검수 요청 ID
    private DadDet dadDet; // 직접광고 상세 ID
    private String cnrIngStatus; // 검수 진행 상태
    private String cnrInputDiv; // 검수 입력 구분
    private Date cnrReqTime; // 검수 요청 시간
    private Date cnrProcTime; // 검수 처리 시간
    private int cnrCompleteYn; // 검수 완료 여부
    private String cnrFailCause; // 검수 실패 사유
    private String cnrFailComt; // 검수 실패 코멘트
}
