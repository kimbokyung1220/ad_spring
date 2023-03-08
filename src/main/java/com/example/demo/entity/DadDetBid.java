package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * 직접광고 상세 입찰
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadDetBid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dadDet; // 직접광고 상세 ID (FK)
    private Integer bidCost; // 입찰 금액
}
