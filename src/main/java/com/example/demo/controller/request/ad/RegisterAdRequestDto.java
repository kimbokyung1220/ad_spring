package com.example.demo.controller.request.ad;

import com.example.demo.controller.request.kwd.KwdRequestDto;
import com.example.demo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdRequestDto {
    private Long agroupId; // 광고그룹 ID(FK)
    private Long cnrId; // 검수요청 ID
    private Long itemId; // 상품ID(FK)
    private List<KwdRequestDto> kwds; // 키워드 리스트
    private String kwdName; // 키워드 이름
    private Integer bidCost; // 입찰 금액
    private String agroupName; // 광고그룹명

    /**
     * 광고 등록
     */
    public Ad createAd(Adv adv, Item item, Agroup agroup) {
        return Ad.builder()
                .agroup(agroup)
                .item(item)
                .adUseConfigYn(1)
                .regTime(LocalDateTime.now())
                .adActYn(1)
                .adv(adv)
                .build();
    }

    /**
     * 광고 직접등록 상세
     */
    public DadDet createDadDet(Ad ad, Kwd kwd) {
        return DadDet.builder()
                .ad(ad)
                .kwd(kwd)
//                .cnrReq()
                .dadCnrStatus("APPROVAL")
                .dadUseConfigYn(1)
                .dadActYn(1)
                .regTime(LocalDateTime.now())
                .build();
    }
    public void update(CnrReq cnrReq) {
        DadDet.builder().cnrReq(cnrReq).build();
    }
}
