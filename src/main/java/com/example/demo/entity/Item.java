package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 상품
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(name = "item_no")
    private String itemNo; // 상품번호
    @Column(name = "item_name")
    private String itemName; // 상품명
    @Column(name = "adult_yn")
    private Integer adultYn; // 성인여부
    @Column(name = "item_org_cost")
    private Integer itemOrgCost; // 상품원본금액
    @Column(name = "item_act_yn")
    private Integer itemActYn; // 상품활성여부
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Ad> ads;
}
