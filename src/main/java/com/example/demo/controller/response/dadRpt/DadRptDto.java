package com.example.demo.controller.response.dadRpt;

import com.example.demo.entity.RptCompositeKey;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class DadRptDto {
    private String basicDate; // 기준날짜
    private Long dadDetId; // 직접상세 ID
    private Long impCnt; // 노출수
    private Long clickCnt; // 클릭수
    private Double avgImpRank; // 평균 노출 순위
    private Double avgCpc; // 평균 클릭 비용
    private Long adSpend; // 광고비
    private Double clickPercent;

    // 광고현황 레포트 출력
    @QueryProjection
    public DadRptDto(String basicDate, Long dadDetId, Long impCnt, Long clickCnt, Double avgImpRank, Double avgCpc, Long adSpend, Double clickPercent) {
        this.basicDate = basicDate;
        this.dadDetId = dadDetId;
        this.impCnt = impCnt;
        this.clickCnt = clickCnt;
        this.avgImpRank = avgImpRank;
        this.avgCpc = avgCpc;
        this.adSpend = adSpend;
        this.clickPercent = clickPercent;
    }
}

