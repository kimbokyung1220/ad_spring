package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 키워드
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Kwd {
    @Id
    @Column(name = "kwd_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kwdId; //키워드 ID
    @Column(name = "kwd_name", nullable = false)
    private String kwdName; // 키워드명
    @Column(name = "sell_poss_kwd_yn", nullable = false)
    private Integer sellPossKwdYn; // 판매 가능 키워드 여부
    @Column(name = "manual_cnr_kwd_yn", nullable = false)
    private Integer manualCnrKwdYn; // 수동 검수 키워드 여부
    @OneToMany(mappedBy = "kwd", cascade = CascadeType.ALL)
    private List<DadDet> DadDets;
}
