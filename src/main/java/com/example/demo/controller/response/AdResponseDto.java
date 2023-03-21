package com.example.demo.controller.response;

import com.example.demo.entity.Ad;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdResponseDto {
    private Long adId; // 광고 ID
    private Agroup agroup; // 광고그룹 ID(FK)
    private Item item; // 상품ID(FK)
    private Integer adUseConfigYn; // 광고 사용 설정 여부
    private ZonedDateTime regTime; // 등록시간
    private Integer adActYn; // 광고 활성 여부
    private Adv adv; // 광고주 ID (FK)

    public static AdResponseDto of(Ad ad) {
        return AdResponseDto.builder()
                .adId(ad.getAdId())
                .agroup(ad.getAgroup())
                .item(ad.getItem())
                .adUseConfigYn(ad.getAdUseConfigYn())
                .regTime(ad.getRegTime())
                .adActYn(ad.getAdActYn())
                .adv(ad.getAdv())
                .build();
    }
}
