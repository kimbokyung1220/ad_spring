package com.example.demo.entity;

import com.example.demo.entity.enm.RoleGroup;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "member")
    @JsonManagedReference
    private Adv adv;

//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
//            return false;
//        }
//        Member member = (Member) o;
//        return memberId != null && Objects.equals(memberId, member.memberId);
//    }

}