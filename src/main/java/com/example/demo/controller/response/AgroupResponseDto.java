package com.example.demo.controller.response;

import com.example.demo.entity.Agroup;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgroupResponseDto {
    private Long agroupId; //광고그룹 ID
    private String agroupName; //광고그룹명
    private ZonedDateTime regTime; // 등록시간
    private Integer agroupActYn; // 광고그룹 활성 여부
    private Integer agroupUseYn; // 광고그룹 사용 설정 여부

    public static AgroupResponseDto of(Agroup agroup) {
        return AgroupResponseDto.builder()
                .agroupId(agroup.getAgroupId())
                .agroupName(agroup.getAgroupName())
                .regTime(agroup.getRegTime())
                .agroupActYn(agroup.getAgroupActYn())
                .agroupUseYn(agroup.getAgroupActYn())
                .build();
    }

}
