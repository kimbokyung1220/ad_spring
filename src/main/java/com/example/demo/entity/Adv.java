package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 광고주
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adv implements Serializable {

    @Id
    @Column
    private String advId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "adv_id", referencedColumnName = "member_id")
    private Member member;
    private Integer advIngActYn; // 광고 진행 활성 여부
    private Integer balance; // 잔액
    private Integer eventMoneyBalance; //이벤트 머니 잔액
    private Integer dayLimitBudget; // 일 제한 예산

    public void addBalance() {
        this.advIngActYn = 1;
        this.balance = 1000000;
        this.eventMoneyBalance = 0;
        this.dayLimitBudget = 0;
    }
}
