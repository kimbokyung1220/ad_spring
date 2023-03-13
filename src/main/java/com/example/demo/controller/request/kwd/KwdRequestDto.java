package com.example.demo.controller.request.kwd;


import com.example.demo.entity.Kwd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KwdRequestDto {
    private String kwdName; // 키워드명
    public Kwd createKwd() {
        return Kwd.builder()
                .kwdName(kwdName)
                .sellPossKwdYn(1)
                .manualCnrKwdYn(1)
                .build();
    }

}
