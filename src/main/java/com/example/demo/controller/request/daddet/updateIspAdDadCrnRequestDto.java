package com.example.demo.controller.request.daddet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class updateIspAdDadCrnRequestDto {
    private Long dadDetId;
    private Long cnrReqId;
    private String cnrIngStatus;
    private String cnrFailCause;
    private String cnrFailComt;
}
