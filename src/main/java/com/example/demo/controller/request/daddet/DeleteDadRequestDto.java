package com.example.demo.controller.request.daddet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDadRequestDto {
    private Long dadDetId;
    private Integer dadActYn;
}
