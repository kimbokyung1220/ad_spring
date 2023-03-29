package com.example.demo.controller.request.kwd;

import com.example.demo.entity.Kwd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KwdNameRequestDto {
    private String kwdName;

    public Kwd saveKwd(){
        return Kwd.builder()
                .kwdName(kwdName)
                .manualCnrKwdYn(1)
                .sellPossKwdYn(1)
                .build();
    }
}
