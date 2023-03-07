package com.example.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 상품
 */
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemNo; // 상품번호
    private String itemName; // 상품명
    private Integer adultYn; // 성인여부
    private Integer itemOrgCost; // 상품원본금액
    private Integer itemActYn; // 상품활성여부
}
