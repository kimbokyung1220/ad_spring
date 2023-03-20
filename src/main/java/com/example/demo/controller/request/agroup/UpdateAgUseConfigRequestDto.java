package com.example.demo.controller.request.agroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgUseConfigRequestDto {
    private Long agroupId; // 광고그룹 ID
    private Integer agroupUseConfigYn; // 광고그룹 사용 설정 여부
}
