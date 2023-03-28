package com.example.demo.controller.request.kwd;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Builder
@NoArgsConstructor
public class KwdDto {
    private Long kwdId; //키워드 ID
    private String kwdName; // 키워드명
    private Integer sellPossKwdYn; // 판매 가능 키워드 여부
    private Integer manualCnrKwdYn; // 수동 검수 키워드 여부

    @QueryProjection
    public KwdDto(Long kwdId, String kwdName, Integer sellPossKwdYn, Integer manualCnrKwdYn) {
        this.kwdId = kwdId;
        this.kwdName = kwdName;
        this.sellPossKwdYn = sellPossKwdYn;
        this.manualCnrKwdYn = manualCnrKwdYn;
    }
}
