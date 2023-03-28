package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 검수 요청
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CnrReq {

    @Id @Column(name="cnr_req_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cnrReqId; // 검수 요청 ID
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 직접광고 상세 ID
    @JoinColumn(name = "dad_det_id")
    private DadDet dadDet;
    @Column(name = "crn_ing_status")
    private String cnrIngStatus; // 검수 진행 상태
    @Column(name = "cnr_input_div")
    private String cnrInputDiv; // 검수 입력 구분
    @Column(name = "cnr_req_time")
    private LocalDateTime cnrReqTime; // 검수 요청 시간
    @Column(name = "cnr_proc_time")
    private LocalDateTime cnrProcTime; // 검수 처리 시간
    @Column(name = "cnr_complete_yn", nullable = false)
    private Integer cnrCompleteYn; // 검수 완료 여부
    @Column(name = "cnr_fail_cause")
    private String cnrFailCause; // 검수 실패 사유
    @Column(name = "cnr_fail_comt")
    private String cnrFailComt; // 검수 실패 코멘트

    // 검수요청 일반등록
    public CnrReq saveCnrReq(DadDet dadDet) {
        return CnrReq.builder()
                .dadDet(dadDet)
                .cnrIngStatus("APPROVAL")
                .cnrInputDiv("INPUT_CNR")
                .cnrReqTime(LocalDateTime.now())
                .cnrProcTime(LocalDateTime.now())
                .cnrCompleteYn(1)
                .build();
    }

    // 검수요청 수동등록
    public CnrReq saveManualCnrReq(DadDet dadDet) {
        return CnrReq.builder()
                .dadDet(dadDet)
                .cnrIngStatus("REQ")
                .cnrInputDiv("INPUT_CNR")
                .cnrReqTime(LocalDateTime.now())
                .cnrProcTime(null)
                .cnrCompleteYn(0)
                .build();
    }
}
