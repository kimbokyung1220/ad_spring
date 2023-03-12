package com.example.demo.controller.response.member;

import com.example.demo.entity.Member;
import com.example.demo.entity.enm.RoleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String memberId;
    private String pwd;
    private RoleGroup roleGroup;

    public static MemberResponseDto of(Optional<Member> member) {
        return MemberResponseDto.builder()
                .memberId(member.get().getMemberId())
                .pwd(member.get().getPwd())
                .roleGroup(member.get().getRoleGroup())
                .build();
    }
}
