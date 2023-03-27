package com.example.demo.controller.response.agroup;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
public class AgroupListResponseDto {

    private Long agroupId; //광고그룹 ID
    private String agroupName; //광고그룹명
    private LocalDateTime regTime; // 등록시간
    private Integer agroupUseConfigYn; // 광고그룹 사용 설정 여부
    private Integer adActYn;  // 광고 활성 여부 상품 개수
    private Integer adUseConfigYn; // 광고 사용 설정 여부 상품 개수

    @QueryProjection
    public AgroupListResponseDto(Long agroupId, String agroupName, LocalDateTime regTime, Integer agroupUseConfigYn, Integer adActYn, Integer adUseConfigYn) {
        this.agroupId = agroupId;
        this.agroupName = agroupName;
        this.regTime = regTime;
        this.agroupUseConfigYn = agroupUseConfigYn;
        this.adActYn = adActYn;
        this.adUseConfigYn = adUseConfigYn;
    }
}
