package com.example.demo.controller.request.daddet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DadUseConfigYnListRequestDto {
    private Integer code;
    private List<DadUseConfigYnRequestDto> dadUseConfigList;
}
