package com.example.demo.controller.request.daddetbio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class DadDetBidRequestDto {
    private Long dadDetId;
    private Integer bidCost; // 입찰 금액

    public DadDetBidRequestDto(Long dadDetId, Integer bidCost) {
        this.dadDetId = dadDetId;
        this.bidCost = bidCost;
    }
}
