package com.example.demo.controller.request.ad;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteAdRequestDto {
    private Long adId;
    private Integer adActYn;
}
