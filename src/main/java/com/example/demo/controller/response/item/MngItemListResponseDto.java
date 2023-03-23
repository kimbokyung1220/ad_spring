package com.example.demo.controller.response.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Builder
@NoArgsConstructor
public class MngItemListResponseDto {
    private Long adId; // 광고ID
    private Long agroupId; //광고그룹 ID
    private String advId; //광고주 ID
    private Integer adUseConfigYn; //광고 사용설정 여부
    private Integer adActYn; // 광고 활성 여부
    private ZonedDateTime regTime; // 광고등록시간
    private Long itemId; //상품ID
    private String itemNo; // 상품번호
    private String itemName; //상품명
    private Integer itemActYn; //상품 활성여부
    @QueryProjection
    public MngItemListResponseDto(Long adId, Long agroupId, String advId, Integer adUseConfigYn, Integer adActYn, ZonedDateTime regTime, Long itemId, String itemNo, String itemName, Integer itemActYn) {
        this.adId = adId;
        this.agroupId = agroupId;
        this.advId = advId;
        this.adUseConfigYn = adUseConfigYn;
        this.adActYn = adActYn;
        this.regTime = regTime;
        this.itemId = itemId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemActYn = itemActYn;
    }
}
