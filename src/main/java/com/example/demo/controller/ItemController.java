package com.example.demo.controller;

import com.example.demo.controller.request.item.ItemRequestDto;
import com.example.demo.controller.response.item.ItemResponseDto;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;
    @PostMapping("/item")
    public ResponseEntity<List<ItemResponseDto>> getItemList(@RequestBody ItemRequestDto itemRequestDto) {

        return ResponseEntity.ok().body(itemService.getItemList(itemRequestDto));
    }
}
