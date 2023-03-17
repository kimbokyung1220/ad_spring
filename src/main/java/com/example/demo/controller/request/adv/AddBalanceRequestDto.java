package com.example.demo.controller.request.adv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceRequestDto {
    private String advId;
    private Integer adIngActYn; // 광고 진행 활성 여부
    private Integer balance; // 잔액
    private Integer eventMoneyBalance; //이벤트 머니 잔액
    private Integer dayLimitBudget; // 일 제한 예산

    public AddBalanceRequestDto (String memberId) {
        this.advId = memberId;
        this.adIngActYn = 1;
        this.balance = 1000000;
        this.eventMoneyBalance = 0;
        this.dayLimitBudget = 0;
    }
}
