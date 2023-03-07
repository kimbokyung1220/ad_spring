package com.example.demo.controller.request;

import com.example.demo.entity.Member;
import com.example.demo.entity.enm.RoleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String memberId;
    private String pwd;

    public Member toAdv(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(memberId)
                .pwd(passwordEncoder.encode(pwd))
                .roleGroup(RoleGroup.ROLE_ADV)
                .build();
    }

    public Member toAdmin(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(memberId)
                .pwd(passwordEncoder.encode(pwd))
                .roleGroup(RoleGroup.ROLE_ADMIN)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(memberId, pwd);
    }
}
