package com.example.demo.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * [ OncePerRequestFilter ]
 * Fileter나 GenericFilterBean이라는 스프링 확장 필터: 매 서블릿 마다 호출이 된다. (서블릿이 다른 서블릿으로 dispatch되는 경)
 * OncePerRequestFilter: 사용자의 한번에 요청 당 딱 한번만 (모든 서블릿에 일관된 요청을 처리하기 위해 만들어진 필터)
 *  - @Override doFilterInternal를 구현해야 함
 *  - 보통 인증 또는 인가와 같이 한번만 거쳐도 되는 로직에서 사용 ( 인증/인가 후 특정 url로 포워딩 -> 인증/인가 필터를 다시 실행 시켜야 하지만, 한번만 거치고 다음 로직으로 진행 가능)
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer "; // type (JWT 혹은 OAuth에 대한 토큰을 사용)

    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = resolveToken(request);

        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
