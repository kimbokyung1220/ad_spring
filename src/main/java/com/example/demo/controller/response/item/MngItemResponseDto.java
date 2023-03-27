package com.example.demo.controller.response.item;

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
public class MngItemResponseDto {
    private Long adId; // 광고ID
    private Long agroupId; //광고그룹 ID
    private String advId; //광고주 ID
    private Integer adUseConfigYn; //광고 사용설정 여부
    private Integer adActYn; // 광고 활성 여부
    private LocalDateTime regTime; // 광고등록시간
    private Long itemId; //상품ID
    private String itemNo; // 상품번호
    private String itemName; //상품명
    private Integer itemActYn; //상품 활성여부
    private String ynStr;

    public static MngItemResponseDto itemList(MngItemListResponseDto dto) {
        return MngItemResponseDto.builder()
                .adId(dto.getAdId())
                .agroupId(dto.getAgroupId())
                .advId(dto.getAdvId())
                .adUseConfigYn(dto.getAdUseConfigYn())
                .adActYn(dto.getAdActYn())
                .regTime(dto.getRegTime())
                .itemId(dto.getItemId())
                .itemNo(dto.getItemNo())
                .itemName(dto.getItemName())
                .itemActYn(dto.getItemActYn())
                .build();
    }
}
