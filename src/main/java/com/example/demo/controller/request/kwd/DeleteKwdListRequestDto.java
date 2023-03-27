package com.example.demo.controller.request.kwd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteKwdListRequestDto {
    private List<DeleteKwdRequestDto> deleteKwdList;
}
