package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn( name = "agroup_id")
    private Agroup agroup; // 광고그룹 ID(FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "item_id")
    private Item item; // 상품ID(FK)
    private Integer adUseConfigYn; // 광고 사용 설정 여부
    private LocalDateTime regTime; // 등록시간
    private Integer adActYn; // 광고 활성 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "adv_id")
    private Adv adv; // 광고주 ID (FK)
            
}
