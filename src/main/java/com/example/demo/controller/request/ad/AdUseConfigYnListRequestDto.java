package com.example.demo.controller.request.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AdUseConfigYnListRequestDto {
    private Integer code;
    private List<AdUseConfigYnRequestDto> adUseConfigYnList;
}
