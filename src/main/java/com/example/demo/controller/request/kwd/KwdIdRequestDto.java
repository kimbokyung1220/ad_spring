package com.example.demo.controller.request.kwd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KwdIdRequestDto {
    private Long kwdId;
    private Integer sellPossKwdYn;
}
