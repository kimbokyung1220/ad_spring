package com.example.demo.controller.request.agroup;

import com.example.demo.entity.Agroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAgroupRequestDto {
    private String agroupName;

    public Agroup createAgroup() {
        return Agroup.builder()
                .agroupName(agroupName)
                .regTime(LocalDateTime.now())
                .agroupActYn(1)
                .agroupUseConfigYn(1)
                .build();
    }
}
