package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 광고 그룹
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agroup {
    @Id
    @Column(name = "agroup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agroupId; //광고그룹 ID

    @ManyToOne
    @JoinColumn(name = "adv_id")
    private Adv adv;

    private String agroupName; //광고그룹명
    private LocalDateTime regTime; // 등록시간
    private Integer agroupActYn; // 광고그룹 활성 여부
    private Integer agroupUseYn; // 광고그룹 사용 설정 여부
}
