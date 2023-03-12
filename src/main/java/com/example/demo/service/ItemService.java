package com.example.demo.service;

import com.example.demo.controller.request.item.ItemRequestDto;
import com.example.demo.controller.response.item.ItemResponseDto;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemResponseDto> getItemList(ItemRequestDto itemRequestDto) {
        List<ItemResponseDto> responseList = new ArrayList<>();

        // 상품명 + 상품번호 조회
        if (!itemRequestDto.getItemNo().equals("") && !itemRequestDto.getItemNo().equals("")) {
            List<Item> itemList = itemRepository.findByItemNameContainingAndItemNoContainingOrderByItemNoAsc(itemRequestDto.getItemName(), itemRequestDto.getItemNo());
            for (Item item : itemList) {
                responseList.add(
                        ItemResponseDto.builder()
                                .itemNo(item.getItemNo())
                                .itemName(item.getItemName())
                                .adultYn(item.getAdultYn())
                                .itemOrgCost(item.getItemOrgCost())
                                .itemActYn(item.getItemActYn())
                                .build()
                );
            }
            System.out.println("상품명 + 상품 번호 조회");
            return responseList;

            // 상품명 조회
        } else if (!itemRequestDto.getItemNo().equals("")) {
            List<Item> itemList = itemRepository.findByItemNoContainingOrderByItemNoAsc(itemRequestDto.getItemNo());
            for (Item item : itemList) {
                responseList.add(
                        ItemResponseDto.builder()
                                .itemNo(item.getItemNo())
                                .itemName(item.getItemName())
                                .adultYn(item.getAdultYn())
                                .itemOrgCost(item.getItemOrgCost())
                                .itemActYn(item.getItemActYn())
                                .build()
                );
            }
            System.out.println("상품번호 조회");
            return responseList;
            // 상품번호 조회
        } else if (!itemRequestDto.getItemName().equals("")) {
            List<Item> itemList = itemRepository.findByItemNameContainingOrderByItemNoAsc(itemRequestDto.getItemName());
            for (Item item : itemList) {
                responseList.add(
                        ItemResponseDto.builder()
                                .itemNo(item.getItemNo())
                                .itemName(item.getItemName())
                                .adultYn(item.getAdultYn())
                                .itemOrgCost(item.getItemOrgCost())
                                .itemActYn(item.getItemActYn())
                                .build()
                );
            }
            System.out.println("상품이름 조회");
            return responseList;
        }
        System.out.println("키워드 없음 조회");
        List<Item> itemList = itemRepository.findAllByOrderByItemNoAsc();
        for (Item item : itemList) {
            responseList.add(
                    ItemResponseDto.builder()
                            .itemNo(item.getItemNo())
                            .itemName(item.getItemName())
                            .adultYn(item.getAdultYn())
                            .itemOrgCost(item.getItemOrgCost())
                            .itemActYn(item.getItemActYn())
                            .build()
            );
        }
        return responseList;
    }
}
