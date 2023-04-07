package com.example.demo.controller.response.agroup;

import com.example.demo.entity.Agroup;
import com.example.demo.util.FormatDateUtil;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgroupResponseDto {
    private Long agroupId; //광고그룹 ID
    private String agroupName; //광고그룹명
    private String regTime; // 등록시간
    private Integer agroupActYn; // 광고그룹 활성 여부
    private Integer agroupUseConfigYn; // 광고그룹 사용 설정 여부
    private Integer adActYn;  // 광고 활성 여부 상품 개수
    private Integer adUseConfigYn; // 광고 사용 설정 여부 상품 개수
    private String agroupUseConfigYnSrt;
    private String itemCnt;

    public static AgroupResponseDto of(Agroup agroup) {
        String time = agroup.getRegTime().format(DateTimeFormatter.ofPattern(FormatDateUtil.fm));
        return AgroupResponseDto.builder()
                .agroupId(agroup.getAgroupId())
                .agroupName(agroup.getAgroupName())
                .regTime(time)
                .agroupActYn(agroup.getAgroupActYn())
                .agroupUseConfigYn(agroup.getAgroupUseConfigYn())
                .build();
    }
    public static AgroupResponseDto agroupItemList(AgroupListResponseDto dto) {

        String time = dto.getRegTime().format(DateTimeFormatter.ofPattern(FormatDateUtil.fm));

        String agroupUseConfigYnSrt = "";
        if(dto.getAgroupUseConfigYn() == 1) {
            agroupUseConfigYnSrt = "ON";
        }else if(dto.getAgroupUseConfigYn() == 0){
            agroupUseConfigYnSrt = "OFF";
        }
        return  AgroupResponseDto.builder()
                .agroupId(dto.getAgroupId())
                .agroupName(dto.getAgroupName())
                .regTime(time)
                .agroupUseConfigYn(dto.getAgroupUseConfigYn())
                .adActYn(dto.getAdActYn())
                .adUseConfigYn(dto.getAdUseConfigYn())
                .agroupUseConfigYnSrt(agroupUseConfigYnSrt)
                .itemCnt(dto.getAdUseConfigYn() + " / " + dto.getAdActYn())
                .build();
    }

    public static AgroupResponseDto agroupItem(AgroupItemResponseDto dto) {
        String time = dto.getRegTime().format(DateTimeFormatter.ofPattern(FormatDateUtil.fm));

        String agroupUseConfigYnSrt = "";
        if(dto.getAgroupUseConfigYn() == 1) {
            agroupUseConfigYnSrt = "ON";
        }else if(dto.getAgroupUseConfigYn() == 0){
            agroupUseConfigYnSrt = "OFF";
        }
        return  AgroupResponseDto.builder()
                .agroupId(dto.getAgroupId())
                .agroupName(dto.getAgroupName())
                .regTime(time)
                .agroupUseConfigYn(dto.getAgroupUseConfigYn())
                .adActYn(dto.getAdActYn())
                .adUseConfigYn(dto.getAdUseConfigYn())
                .agroupUseConfigYnSrt(agroupUseConfigYnSrt)
                .itemCnt(dto.getAdUseConfigYn() + " / " + dto.getAdActYn())
                .build();
    }

}
