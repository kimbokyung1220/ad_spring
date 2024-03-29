package com.example.demo.controller.response.kwd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class KwdResponseDto {
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
    private Integer sellPossKwdYn; // 판매 가능 키워드 여부
    private Integer manualCnrKwdYn; // 수동 검수 키워드 여부

    @Getter
    public static class kwdListDto {
        public static KwdResponseDto kwdList(KwdDto kwdDto) {
            return KwdResponseDto.builder()
                    .adId(kwdDto.getAdId())
                    .agroupId(kwdDto.getAgroupId())
                    .adUseConfigYn(kwdDto.getAdUseConfigYn())
                    .adActYn(kwdDto.getAdActYn())
                    .regTime(kwdDto.getRegTime())
                    .dadDetId(kwdDto.getDadDetId())
                    .dadActYn(kwdDto.getDadActYn())
                    .dadUseConfigYn(kwdDto.getDadUseConfigYn())
                    .kwdId(kwdDto.getKwdId())
                    .kwdName(kwdDto.getKwdName())
                    .build();
        }
    }

    // 검수 키워드 리스트
    @Getter
    public static class ispKwdListDto {
        public static KwdResponseDto ispKwdList(KwdDto kwdDto) {
            return KwdResponseDto.builder()
                    .kwdId(kwdDto.getKwdId())
                    .kwdName(kwdDto.getKwdName())
                    .build();
        }
    }
}
