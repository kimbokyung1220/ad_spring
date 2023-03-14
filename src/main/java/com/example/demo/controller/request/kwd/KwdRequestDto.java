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
}
