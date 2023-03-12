package com.example.demo.entity;

import com.example.demo.controller.request.member.AddBalanceRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 광고주
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adv implements Serializable {

    public Adv(Member member) {
        this.member = member;
    }

    @Id
    @Column(name = "adv_id")
    private String advId;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "adv_id", referencedColumnName = "member_id")
    private Member member;
    @Column(name = "adv_ing_act_yn")
    private Integer advIngActYn; // 광고 진행 활성 여부
    @Column(name = "balance")
    private Integer balance; // 잔액
    @Column(name = "event_money_balance")
    private Integer eventMoneyBalance; //이벤트 머니 잔액
    @Column(name = "day_limit_budget")
    private Integer dayLimitBudget; // 일 제한 예산

    @OneToMany(mappedBy = "adv", cascade = CascadeType.ALL)
    private List<Agroup> agroups;


    public Adv addBalance(AddBalanceRequestDto dto) {
       return Adv.builder()
               .advId(dto.getAdvId())
               .member(member)
               .advIngActYn(dto.getAdvIngActYn())
               .balance(dto.getBalance())
               .eventMoneyBalance(dto.getEventMoneyBalance())
               .dayLimitBudget(dto.getDayLimitBudget())
               .build();
    }
}
