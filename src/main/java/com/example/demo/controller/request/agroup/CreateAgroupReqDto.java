package com.example.demo.controller.request.agroup;

import com.example.demo.entity.Adv;
import com.example.demo.entity.Agroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class CreateAgroupReqDto {
    private String agroupName;
//    private ZonedDateTime regTime;
    public Agroup createAgroup(Adv adv) {
        return Agroup.builder()
                .agroupName(agroupName)
//                .regTime()
                .agroupActYn(1)
                .agroupUseYn(1)
                .adv(adv)
                .build();
    }
}
