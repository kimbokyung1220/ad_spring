package com.example.demo.controller.response.item;

import com.example.demo.controller.response.member.MemberResponseDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {
    private Long itemId; // 상품 ID
    private String itemNo; // 상품번호
    private String itemName; // 상품명
    private String adultYn; // 성인여부
    private Integer itemOrgCost; // 상품원본금액
    private String itemActYn; // 상품활성여부

    public static ItemResponseDto of(Item item) {
        String adultYn = "";
        String itemActYn = "";

        if (item.getAdultYn() == 1) {
            adultYn = "YES";
        } else if (item.getAdultYn() == 0) {
            adultYn = "NO";
        }

        if (item.getItemActYn() == 1) {
            itemActYn = "활성화";
        } else if (item.getItemActYn() == 0) {
            itemActYn = "비활성화";
        }

        return ItemResponseDto.builder()
                .itemId(item.getItemId())
                .itemNo(item.getItemNo())
                .itemName(item.getItemName())
                .adultYn(adultYn)
                .itemOrgCost(item.getItemOrgCost())
                .itemActYn(itemActYn)
                .build();
    }
}
