package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadRpt {
    @EmbeddedId
    private RptCompositeKey rptCompositeKey; // advId, basicDate, dadDetId

    @Column(name = "imp_cnt")
    private Long impCnt; // 노출수

    @Column(name = "click_cnt")
    private Long clickCnt; // 클릭수

    @Column(name = "avg_imp_rank")
    private Double avgImpRank; // 평균 노출 순위

    @Column(name = "avg_cpc")
    private Double avgCpc; // 평균 클릭 비용

    @Column(name = "ad_spend")
    private Long adSpend; // 광고비

    @Transient
    private String filePath;
}
