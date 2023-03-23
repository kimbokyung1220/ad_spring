package com.example.demo.repository;

import com.example.demo.controller.request.agroup.AgroupNameRequestDto;
import com.example.demo.controller.request.item.ItemNoNameRequestDto;
import com.example.demo.controller.response.agroup.AgroupListResponseDto;
import com.example.demo.controller.response.item.MngItemListResponseDto;

import java.util.List;

public interface ItemDslRepository {
    List<MngItemListResponseDto> searchItemList(Long agroupId, ItemNoNameRequestDto noNameRequestDto);
}
