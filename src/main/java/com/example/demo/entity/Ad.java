package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * 광고
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
    @Id @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId; // 광고 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "agroup_id", nullable = false)
    private Agroup agroup; // 광고그룹 ID(FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "item_id", nullable = false)
    private Item item; // 상품ID(FK)
    @Column(name = "ad_use_config_yn", nullable = false)
    private Integer adUseConfigYn; // 광고 사용 설정 여부
    @Column(name = "reg_time", nullable = false)
    private ZonedDateTime regTime; // 등록시간
    @Column(name = "ad_act_yn", nullable = false)
    private Integer adActYn; // 광고 활성 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "adv_id", nullable = false)
    private Adv adv; // 광고주 ID (FK)
            
}
