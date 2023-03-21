package com.example.demo.controller.request.agroup;

import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAgroupRequestDto {
    private String agroupName;
    public Agroup createAgroup() {
        return Agroup.builder()
                .agroupName(agroupName)
                .regTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .agroupActYn(1)
                .agroupUseConfigYn(1)
                .build();
    }
}