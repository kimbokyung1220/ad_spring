package com.example.demo.controller.response.agroup;

import com.example.demo.entity.Agroup;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgroupResponseDto {
    private Long agroupId; //광고그룹 ID
    private String agroupName; //광고그룹명
    private ZonedDateTime regTime; // 등록시간
    private Integer agroupActYn; // 광고그룹 활성 여부
    private Integer agroupUseConfigYn; // 광고그룹 사용 설정 여부
    private Integer adActYn;  // 광고 활성 여부 상품 개수
    private Integer adUseConfigYn; // 광고 사용 설정 여부 상품 개수
    private String agroupUseConfigYnSrt;
    private String itemCnt;

    public static AgroupResponseDto of(Agroup agroup) {
        return AgroupResponseDto.builder()
                .agroupId(agroup.getAgroupId())
                .agroupName(agroup.getAgroupName())
                .regTime(agroup.getRegTime())
                .agroupActYn(agroup.getAgroupActYn())
                .agroupUseConfigYn(agroup.getAgroupUseConfigYn())
                .build();
    }
    public static AgroupResponseDto agroupItem(AgroupListResponseDto dto) {
        String agroupUseConfigYnSrt = "";
        if(dto.getAgroupUseConfigYn() == 1) {
            agroupUseConfigYnSrt = "ON";
        }else if(dto.getAgroupUseConfigYn() == 0){
            agroupUseConfigYnSrt = "OFF";
        }
        return  AgroupResponseDto.builder()
                .agroupId(dto.getAgroupId())
                .agroupName(dto.getAgroupName())
                .regTime(dto.getRegTime())
                .agroupUseConfigYn(dto.getAgroupUseConfigYn())
                .adActYn(dto.getAdActYn())
                .adUseConfigYn(dto.getAdUseConfigYn())
                .agroupUseConfigYnSrt(agroupUseConfigYnSrt)
                .itemCnt(dto.getAdUseConfigYn() + " / " + dto.getAdActYn())
                .build();
    }

}
