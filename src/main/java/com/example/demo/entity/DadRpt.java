package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DadRpt {

    @Id
    @Column(name = "adv_id")
    private String advId; // 광고주 ID

    @Id
    @Column(name = "basic_date")
    private LocalDateTime basicDate; // 광고주 ID

    @Id
    @Column(name = "dad_det_id")
    private Long dadDetId; // 광고주 ID

    @Column(name = "imp_cnt")
    private Integer impCnt; // 노출수

    @Column(name = "click_cnt")
    private Integer clickCnt; // 클릭수

    @Column(name = "avg_imp_rank")
    private Double avgImpRank; // 평균 노출 순위

    @Column(name = "avg_cpc")
    private Long avgCpc; // 평균 클릭 비용

    @Column(name = "ad_spend")
    private Integer adSpend; // 광고비
}
