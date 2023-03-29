package com.example.demo.controller.response.dadDet;

import com.example.demo.controller.response.kwd.KwdDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class IspAdListResponseDto {
    private Long kwdId;
    private String kwdName;
    private Long itemId;
    private String itemName;
    private Long dadDetId;
    private Long cnrReqId;

    public static IspAdListResponseDto IspAdList(DadDetDto dadDetDto) {
        return IspAdListResponseDto.builder()
                .kwdId(dadDetDto.getKwdId())
                .kwdName(dadDetDto.getKwdName())
                .itemId(dadDetDto.getItemId())
                .itemName(dadDetDto.getItemName())
                .dadDetId(dadDetDto.getDadDetId())
                .cnrReqId(dadDetDto.getCnrReqId())
                .build();
    }
}
