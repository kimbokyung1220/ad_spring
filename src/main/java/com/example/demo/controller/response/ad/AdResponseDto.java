package com.example.demo.controller.response.ad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdResponseDto {
    private Long memberId;
    private Long itemId;
    private Long agroupId;

}
