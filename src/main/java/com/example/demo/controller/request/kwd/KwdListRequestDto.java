package com.example.demo.controller.request.kwd;

import com.example.demo.controller.request.agroup.DeleteAgroupRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KwdListRequestDto {
    private Integer code;
    private List<KwdIdRequestDto> kwdList;
}
