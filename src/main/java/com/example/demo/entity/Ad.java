package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 광고
 */
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId; // 광고 ID
    @ManyToOne
    private Agroup agroup; // 광고그룹 ID(FK)
    @ManyToOne
    private Item item; // 상품ID(FK)
    private Integer adUseConfigYn; // 광고 사용 설정 여부
    private LocalDateTime regTime; // 등록시간
    private Integer adActYn; // 광고 활성 여부
    @ManyToOne
    private Adv adv; // 광고주 ID (FK)
            
}
