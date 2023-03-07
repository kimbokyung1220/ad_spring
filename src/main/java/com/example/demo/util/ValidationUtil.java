package com.example.demo.util;

import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final MemberRepository memberRepository;

    public String existMember(String memberId) {
        if (memberRepository.existsById(memberId)) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        return memberId;
    }
}
