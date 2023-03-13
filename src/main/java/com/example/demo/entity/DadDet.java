package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 직접광고 상세
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadDet {
    @Id @Column(name = "ada_det_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dadDetId; // 직접광고 상세 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad", nullable = false)
    private Ad ad; // 광고 ID (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kwd", nullable = false)
    private Kwd kwd; // 키워드 ID (FK)
    @Column(name = "dad_cnr_status", nullable = false)
    private String dadCnrStatus; // 직접광고 검수 상태
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnrReq", nullable = false)
    private CnrReq cnrReq; // 검수 요청 ID (FK)
    @Column(name = "dad_use_config_yn", nullable = false)
    private Integer dadUseConfigYn; // 직접광고 사용 설정 여부
    @Column(name = "dad_act_yn", nullable = false)
    private Integer dadActYn; // 직접광고 활성 여부
    @Column(name = "reg_time", nullable = false)
    private LocalDateTime regTime; // 등록시간
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cnr_req_id", nullable = false)
    private CnrReq cnrReqs;
    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "dadDet", cascade = CascadeType.ALL)
    @JsonManagedReference
    private DadDetBid dadDetBid;
}
