package com.example.demo.controller.request.kwd;


import com.example.demo.entity.Kwd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KwdRequestDto {
    private Long key;
    private String kwdName;
    private Integer bidCost;
}
