package com.example.demo.controller.response.kwd;

import com.example.demo.entity.DadDet;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class KwdDto {
    private Long adId; // 광고ID
    private Long agroupId; //광고그룹 ID
    private Integer adUseConfigYn; //광고 사용설정 여부
    private Integer adActYn; // 광고 활성 여부
    private LocalDateTime regTime; // 광고등록시간
    private Long dadDetId; // 직접광고 상세ID
    private Integer dadActYn; // 직접광고 활성 여부
    private Integer dadUseConfigYn; // 직접광고 사용 설정 여부
    private Long kwdId; //키워드 ID
    private String kwdName; // 키워드명
    private Long itemId;
    private String itemName;
    private Long cnrReqId;


    // 키워드 검색
    @QueryProjection
    public KwdDto(Long adId, Long agroupId, Integer adUseConfigYn, Integer adActYn, LocalDateTime regTime, Long dadDetId, Integer dadActYn, Integer dadUseConfigYn, Long kwdId, String kwdName) {
        this.adId = adId;
        this.agroupId = agroupId;
        this.adUseConfigYn = adUseConfigYn;
        this.adActYn = adActYn;
        this.regTime = regTime;
        this.dadDetId = dadDetId;
        this.dadActYn = dadActYn;
        this.dadUseConfigYn = dadUseConfigYn;
        this.kwdId = kwdId;
        this.kwdName = kwdName;
    }

    // 검수 키워드 검색
    @QueryProjection
    public KwdDto(Long kwdId, String kwdName) {
        this.kwdId = kwdId;
        this.kwdName = kwdName;
    }
    // 광고 검수 대상 검색
    @QueryProjection
    public KwdDto(Long dadDetId, Long kwdId, String kwdName, Long itemId, String itemName, Long cnrReqId) {
        this.dadDetId = dadDetId;
        this.kwdId = kwdId;
        this.kwdName = kwdName;
        this.itemId = itemId;
        this.itemName = itemName;
        this.cnrReqId = cnrReqId;
    }
}
