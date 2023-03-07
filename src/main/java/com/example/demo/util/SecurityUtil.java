package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    //util의 속성을 가지기 위헤 private로 생성자를 생성 => so, new로 만들지 못함요
    private SecurityUtil() { }

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    // 쓰레드로컬) +
    public static Long getCurrentMemberId() {
        // SecurityContext 는 ThreadLocal 에 사용자의 정보를 저장
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }

    public static String getCurrentAuth() {
        // SecurityContext 는 ThreadLocal 에 사용자의 정보를 저장
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        if (authentication == null || !String.valueOf(authentication.getAuthorities()).equals("[ROLE_ADMIN]")) {

            throw  new RuntimeException("admin이 아니에요");
        }

        return authentication.getAuthorities().toString();
    }
}