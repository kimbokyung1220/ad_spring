package com.example.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {
    private String itemNo; // 상품번호
    private String itemName; // 상품명
}
