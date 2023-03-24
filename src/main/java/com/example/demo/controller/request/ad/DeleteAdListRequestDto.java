package com.example.demo.controller.request.ad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeleteAdListRequestDto {
    private List<DeleteAdRequestDto> deleteAdList;
}

