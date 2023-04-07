package com.example.demo.controller.response.dadRpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadRptResultResponseDto {
    private String basicDate; // 기준날짜
    private Long dadDetId; // 직접상세 ID
    private Long impCnt; // 노출수
    private Long clickCnt; // 클릭수
    private Double avgImpRank; // 평균 노출 순위
    private Double avgCpc; // 평균 클릭 비용
    private Long adSpend; // 광고비
    private Double clickPercent;

//    public static DadRptResultResponseDto of(DadRptDto dadRptDto) {
//        return DadRptResultResponseDto.builder()
//                .basicDate(dadRptDto.getBasicDate())
//                .dadDetId(dadRptDto.getDadDetId())
//                .impCnt(dadRptDto.getImpCnt())
//                .clickCnt(dadRptDto.getClickCnt())
//                .avgImpRank(dadRptDto.getAvgImpRank())
//                .avgCpc(dadRptDto.getAvgCpc())
//                .adSpend(dadRptDto.getAdSpend())
//                .clickPercent(dadRptDto.getClickPercent())
//                .build();
//    }
}
