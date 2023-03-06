package com.example.demo.entity;

import java.util.Date;

/**
 * 광고
 */
public class Ad {
    private Long adId; // 광고 ID
    private Agroup agroup; // 광고그룹 ID(FK)
    private Item item; // 상품ID(FK)
    private int adUseConfigYn; // 광고 사용 설정 여부
    private Date regTime; // 등록시간
    private int adActYn; // 광고 활성 여부
    private Adv adv; // 광고주 ID (FK)
            
}
