package com.example.demo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {
    private Long itemId; // 상품ID
    private String itemNo; // 상품번호
    private String itemName; // 상품명
    private Integer adultYn; // 성인여부
    private Integer itemOrgCost; // 상품원본금액
    private Integer itemActYn; // 상품활성여부
}
