package com.example.demo.controller.request.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AdUseConfigYnRequestDto {
    private Long adId;
    private Long itemId;
    private Integer adUseConfigYn;
}
