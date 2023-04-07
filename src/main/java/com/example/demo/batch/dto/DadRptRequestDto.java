package com.example.demo.batch.dto;

import com.example.demo.entity.DadRpt;
import com.example.demo.entity.RptCompositeKey;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadRptRequestDto {
    private String basicDate; // 기준날짜
    private Long dadDetId; // 직접광고 상세 ID
    private Long impCnt; // 노출수
    private Long clickCnt; // 클릭수
    private Double avgImpRank; // 평균 노출 순위
    private Double avgCpc; // 평균 클릭 비용
    private Long adSpend; // 광고비

    public DadRpt of(String advId) {
        RptCompositeKey rptCompositeKey = new RptCompositeKey(advId, basicDate, dadDetId);
        return DadRpt.builder()
                .rptCompositeKey(rptCompositeKey)
                .impCnt(impCnt)
                .clickCnt(clickCnt)
                .avgImpRank(avgImpRank)
                .avgCpc(avgCpc)
                .adSpend(adSpend)
                .build();
    }

//    public Temp of(String advId, DadRptRequestDto existingData) {
//        return Temp.builder()
//                .basicDate(existingData.basicDate)
//                .advId(advId)
//                .dadDetId(existingData.dadDetId)
//                .impCnt(existingData.impCnt)
//                .clickCnt(existingData.clickCnt)
//                .avgImpRank(existingData.avgImpRank)
//                .avgCpc(existingData.avgCpc)
//                .adSpend(existingData.adSpend)
//                .build();
//    }
//    public DadRpt of2(String advId) {
//        return DadRpt.builder()
//                .basicDate(basicDate)
//                .advId(advId)
//                .dadDetId(dadDetId)
//                .impCnt(impCnt)
//                .clickCnt(clickCnt)
//                .avgImpRank(avgImpRank)
//                .avgCpc(avgCpc)
//                .adSpend(adSpend)
//                .build();
//    }

}
