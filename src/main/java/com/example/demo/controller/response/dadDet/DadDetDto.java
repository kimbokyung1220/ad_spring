package com.example.demo.controller.response.dadDet;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadDetDto {
    private Long dadDetId; // 직접광고 상세ID
    private Long kwdId; //키워드 ID
    private String kwdName; // 키워드명
    private Long itemId;
    private String itemName;
    private Long cnrReqId;
    private Integer adultYn;

    // 광고 검수 대상 검색
    @QueryProjection
    public DadDetDto(Long dadDetId, Long kwdId, String kwdName, Long itemId, String itemName, Long cnrReqId) {
        this.dadDetId = dadDetId;
        this.kwdId = kwdId;
        this.kwdName = kwdName;
        this.itemId = itemId;
        this.itemName = itemName;
        this.cnrReqId = cnrReqId;
    }
    @QueryProjection

    public DadDetDto(Long dadDetId, String kwdName, String itemName, Integer adultYn) {
        this.dadDetId = dadDetId;
        this.kwdName = kwdName;
        this.itemName = itemName;
        this.adultYn = adultYn;
    }
}
