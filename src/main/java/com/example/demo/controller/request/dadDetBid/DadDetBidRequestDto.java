package com.example.demo.controller.request.dadDetBid;

import com.example.demo.entity.DadDet;
import com.example.demo.entity.DadDetBid;

import javax.persistence.*;

public class DadDetBidRequestDto {
    private Integer bidCost; // 입찰 금액

    public DadDetBid updateBidCost() {
        return DadDetBid.builder()
                .bidCost(bidCost)
                .build();
    }
}
