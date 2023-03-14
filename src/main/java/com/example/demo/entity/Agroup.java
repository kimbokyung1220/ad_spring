package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 광고 그룹
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agroup {
    @Id @Column(name = "agroup_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agroupId; //광고그룹 ID
    @Column(name="agroup_name", nullable = false)
    private String agroupName; //광고그룹명
    @Column(name="reg_time")
    private ZonedDateTime regTime; // 등록시간
    @Column(name="agroup_act_yn", nullable = false)
    private Integer agroupActYn; // 광고그룹 활성 여부
    @Column(name="agroup_use_yn", nullable = false)
    private Integer agroupUseYn; // 광고그룹 사용 설정 여부
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adv")
    private Adv adv;

}
