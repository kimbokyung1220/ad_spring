package com.example.demo.controller.request.ad;

import com.example.demo.entity.Ad;
import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdRequestDto {
    private Long agroupId; // 광고그룹 ID(FK)
    private Long itemId; // 상품ID(FK)
//    private Integer adUseConfigYn; // 광고 사용 설정 여부 "지우기"
//    private ZonedDateTime regTime; // 등록시간 "지우기"
//    private Integer adActYn; // 광고 활성 여부 "지우기"

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
}
