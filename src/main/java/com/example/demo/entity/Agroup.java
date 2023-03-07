package com.example.demo.entity;

import java.time.LocalDateTime;

/**
 * 광고 그룹
 */
public class Agroup {
    private Long agroupId; //광고그룹 ID
    private String agroupName; //광고그룹명
    private LocalDateTime regTime; // 등록시간
    private Integer agroupActYn; // 광고그룹 활성 여부
}
