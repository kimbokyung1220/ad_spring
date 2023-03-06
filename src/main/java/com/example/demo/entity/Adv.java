package com.example.demo.entity;

import javax.persistence.Column;

/**
 * 광고주
 */
public class Adv {
    private Member member; // memberId = advId
//    @Column
    private int advIngActYn; // 광고 진행 활성 여부
//    @Column
    private String Balance; // 잔액
//    @Column
    private String eventMoneyBalance; //이벤트 머니 잔액
//    @Column
    private String dayLimitBudget; // 일 제한 예산
}
