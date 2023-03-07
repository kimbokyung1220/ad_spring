package com.example.demo.entity;

import com.example.demo.entity.enm.RoleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 회원
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @Column(name="member_id")
    private String memberId; // 아이디
    @Column
    private String pwd;  // 비번
    @Enumerated(EnumType.STRING)
    private RoleGroup roleGroup;
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Adv adv;
}