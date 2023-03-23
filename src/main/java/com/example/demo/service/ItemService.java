package com.example.demo.service;

import com.example.demo.controller.request.item.ItemNoNameRequestDto;
import com.example.demo.controller.request.item.ItemRequestDto;
import com.example.demo.controller.response.item.MngItemListResponseDto;
import com.example.demo.controller.response.item.MngItemResponseDto;
import com.example.demo.controller.response.item.RegItemResponseDto;
import com.example.demo.entity.Agroup;
import com.example.demo.entity.Item;
import com.example.demo.repository.AgroupDslRepositoryImpl;
import com.example.demo.repository.ItemDslRepositoryImpl;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.common.ValidationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ValidationService validation;
    private final ItemRepository itemRepository;
    private final ItemDslRepositoryImpl itemDslRepository;

    public List<RegItemResponseDto> getItemList(ItemRequestDto itemRequestDto) {
        List<RegItemResponseDto> responseList = new ArrayList<>();

        // 상품명 + 상품번호 조회
        if (!itemRequestDto.getItemNo().equals("") && !itemRequestDto.getItemNo().equals("")) {
            List<Item> itemList = itemRepository.findByItemNameContainingAndItemNoContainingOrderByItemNoAsc(itemRequestDto.getItemName(), itemRequestDto.getItemNo());
            for (Item item : itemList) {
                responseList.add(
                        RegItemResponseDto.of(item)
                );
            }
            System.out.println("상품명 + 상품 번호 조회");
            return responseList;

            // 상품명 조회
        } else if (!itemRequestDto.getItemNo().equals("")) {
            List<Item> itemList = itemRepository.findByItemNoContainingOrderByItemNoAsc(itemRequestDto.getItemNo());
            for (Item item : itemList) {
                responseList.add(
                        RegItemResponseDto.of(item)
                );
            }
            System.out.println("상품번호 조회");
            return responseList;
            // 상품번호 조회
        } else if (!itemRequestDto.getItemName().equals("")) {
            List<Item> itemList = itemRepository.findByItemNameContainingOrderByItemNoAsc(itemRequestDto.getItemName());
            for (Item item : itemList) {
                responseList.add(
                        RegItemResponseDto.of(item)
                );
            }
            System.out.println("상품이름 조회");
            return responseList;
        }
        System.out.println("키워드 없음 조회");
        List<Item> itemList = itemRepository.findAllByOrderByItemNoAsc();
        for (Item item : itemList) {
            responseList.add(
                    RegItemResponseDto.of(item)
            );
        }
        return responseList;
    }

    public List<MngItemResponseDto> searchItemList(Long agroupId, ItemNoNameRequestDto noNameRequestDto, HttpServletRequest servletRequest) {
        Agroup agroup = validation.isPresentAgroup(agroupId);
        List<MngItemListResponseDto> dtoList = itemDslRepository.searchItemList(agroupId, noNameRequestDto);
        List<MngItemResponseDto> result = new ArrayList<>();

        for (MngItemListResponseDto dto : dtoList) {
            result.add(
                    MngItemResponseDto.itemList(dto)
            );
        }
        return result;
    }
}
