package com.example.demo.controller.request.ad;

import com.example.demo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdRequestDto {
    private Long agroupId; // 광고그룹 ID(FK)
    private Long itemId; // 상품ID(FK)
    private String kwdName; // 키워드
    private Integer bidCost; // 입찰 금액


    public Ad createAd(Adv adv, Item item, Agroup agroup) {
        return Ad.builder()
                .agroup(agroup)
                .item(item)
                .adUseConfigYn(1)
                .regTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .adActYn(1)
                .adv(adv)
                .build();
    }

    public Kwd createKwd() {
        return Kwd.builder()
                .kwdName(kwdName)
                .sellPossKwdYn(1)
                .manualCnrKwdYn(0)
                .build();
    }

    public DadDet createDadDet(Ad ad, Kwd kwd) {
        return DadDet.builder()
                .ad(ad)
                .kwd(kwd)
                .dadCnrStatus("APPROVAL")
                .dadUseConfigYn(1)
                .dadActYn(1)
                .regTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }
}
