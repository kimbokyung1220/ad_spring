package com.example.demo.controller.response;

import com.example.demo.entity.Adv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.demo.entity.enm.AdvInfo.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvResponseDto {

    private String advId;
    private Integer adIngActYn; // 광고 진행 활성 여부
    private Integer balance; // 잔액
    private Integer chargingAmount; // 충전 금액 (잔액 + 이벤트 머니 잔액)
    private Integer eventMoneyBalance; //이벤트 머니 잔액
    private Integer dayLimitBudget; // 일 제한 예산
    private String chargingAmountStatus; // 잔액 상태
    private String dayLimitBudgetStatus; // 일 제한 예산
    private String msg;


    public static AdvResponseDto advInfo(Adv adv) {
        Integer chargingAmount = adv.getBalance() + adv.getEventMoneyBalance();
        String chargingAmountStatus = "";
        String dayLimitBudgetStatus = "";

        if (chargingAmount > 0) {
            chargingAmountStatus = HAVE_BALANCE.getValue();
        } else if (chargingAmount == 0) {
            chargingAmountStatus = NOHAVE_BALANCE.getValue();
        }

        if (adv.getDayLimitBudget() == 0) {
            dayLimitBudgetStatus = FREE.getValue();
        }

        return AdvResponseDto.builder()
                .advId(adv.getAdvId())
                .adIngActYn(adv.getAdIngActYn())
                .balance(adv.getBalance())
                .chargingAmount(chargingAmount)
                .chargingAmountStatus(chargingAmountStatus)
                .eventMoneyBalance(adv.getEventMoneyBalance())
                .dayLimitBudget(adv.getDayLimitBudget())
                .dayLimitBudgetStatus(dayLimitBudgetStatus)
                .build();
    }

    public static AdvResponseDto sendMsg(String msg) {
        return AdvResponseDto.builder()
                .msg(msg)
                .build();
    }

}
