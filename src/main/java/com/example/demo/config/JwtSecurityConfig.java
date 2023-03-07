package com.example.demo.config;

import com.example.demo.config.jwt.JwtFilter;
import com.example.demo.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 직접 만든 TokenProvider 와 JwtFilter 를 SecurityConfig 에 적용할 때 사용
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final TokenProvider tokenProvider;

    // 실제 필터링 로직 => JWT 토큰의 인증정보를 현재 실행중인 시큐리티 컨텍스트에 저장하기 위한 역할
    // TokenProvider 를 주입받아서 JwtFilter를 Security 로직에(UsernamePasswordAuthenticationFilter 직전에) 필터를 등록
    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

/**
 * SecurityContext를 쓰기 위해서 앞단의 필터들을 지나야 하므로 UsernamePasswordAuthenticationFilter 에서도 SecurityContext를 사용하는 것으로 보아
 * (정확히는 AbstractAuthenticationProcessingFilter 의 successfulAuthentication 메서드) UsernamePasswordAuthenticationFilter 이전에 추가
 */