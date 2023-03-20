package com.example.demo.controller.request.agroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchAgroupRequestDto {
    private String agroupName;
}
