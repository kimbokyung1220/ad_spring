package com.example.demo.controller.request.agroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgUseConfigListRequestDto {
    private Integer code;
    private List<UpdateAgUseConfigRequestDto> agUseConfigList;
}
