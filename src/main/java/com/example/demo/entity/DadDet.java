package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn( name = "ad_id")
    private Ad ad; // 광고 ID (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "kwd_id")
    private Kwd kwd; // 키워드 ID (FK)
    private String dadCnrStatus; // 직접광고 검수 상태
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "cnrReq_id")
    private CnrReq cnrReq; // 검수 요청 ID (FK)
    private Integer dadUseConfigYn; // 직접광고 사용 설정 여부
    private Integer dadActYn; // 직접광고 활성 여부
    private LocalDateTime regTime; // 등록시간
}
