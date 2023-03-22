package com.example.demo.repository;

import com.example.demo.controller.request.agroup.AgroupIdRequestDto;
import com.example.demo.controller.request.agroup.AgroupNameRequestDto;
import com.example.demo.controller.response.agroup.AgroupItemResponseDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;

import java.util.List;

public interface AgroupDslRepository {

    List<AgroupListResponseDto> searchAgroupList(AgroupNameRequestDto agroupRequestDto);
    AgroupItemResponseDto agroupDetail(AgroupIdRequestDto dto);
}
