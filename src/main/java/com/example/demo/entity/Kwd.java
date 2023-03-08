package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kwdId; //키워드 ID
    private String kwdName; // 키워드명
    private Integer sellPossKwdYn; // 판매 가능 키워드 여부
    private Integer manualCnrKwdYn; // 수동 검수 키워드 여부
}
