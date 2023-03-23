package com.example.demo.controller;

import com.example.demo.controller.request.item.ItemNoNameRequestDto;
import com.example.demo.controller.request.item.ItemRequestDto;
import com.example.demo.controller.response.item.MngItemListResponseDto;
import com.example.demo.controller.response.item.MngItemResponseDto;
import com.example.demo.controller.response.item.RegItemResponseDto;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    /** 상품 전체조회 - [광고등록] */
    @PostMapping("/item")
    public ResponseEntity<List<RegItemResponseDto>> getItemList(@RequestBody ItemRequestDto itemRequestDto) {

        return ResponseEntity.ok().body(itemService.getItemList(itemRequestDto));
    }
    /** 상품 조건조회 - [광고관리] */
    @PostMapping("/item/list/{agroupId}")
    public ResponseEntity<List<MngItemResponseDto>> searchItemList(@PathVariable Long agroupId, @RequestBody ItemNoNameRequestDto noNameRequestDto, HttpServletRequest servletRequest) {

        return ResponseEntity.ok().body(itemService.searchItemList(agroupId, noNameRequestDto, servletRequest));
    }

}
