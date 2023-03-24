package com.example.demo.controller.request.daddet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DadUseConfigYnRequestDto {
    private Long dadDetId;
    private Integer dadUseConfigYn;
}
