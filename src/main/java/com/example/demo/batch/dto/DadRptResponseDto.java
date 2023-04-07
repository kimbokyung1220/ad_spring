package com.example.demo.batch.dto;

import com.example.demo.entity.RptCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DadRptResponseDto {
    private RptCompositeKey rptCompositeKey; // advId, basicDate, dadDetId
    private Long impCnt; // 노출수
    private Long clickCnt; // 클릭수
    private Double avgImpRank; // 평균 노출 순위
    private Double avgCpc; // 평균 클릭 비용
    private Long adSpend; // 광고비
}
