package com.example.demo.config.jwt;

import com.example.demo.controller.response.ResponseDto;
import com.example.demo.service.common.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter { //OncePerRequestFilter 인터페이스를 구현하기 때문에 요청 받을 때 단 한번만 실행

    public static String AUTHORIZATION_HEADER = "Authorization";
    public static String BEARER_PREFIX = "Bearer ";
    public static String AUTHORITIES_KEY = "auth";
    private final String SECRET_KEY;
    private final TokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    //실제 필터링 로직은 doFilterInternal에 들어감
    //JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할을 수행
    // 현재는 jwtFilter 통과 시 loadUserByUsername을 호출하여 디비를 거치지 않으므로 시큐리티 컨텍스트에는 엔티티 정보를 온전히 가지지 않는다
    // 즉 loadUserByUsername을 호출하는 인증 API를 제외하고는 유저네임, 권한만 가지고 있으므로 유저 정보가 필요하다면 디비에서 꺼내와야함
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws io.jsonwebtoken.io.IOException, ServletException, java.io.IOException {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        //1. Request Header에서 토큰을 가져옴
        String jwt = resolveToken(request);

        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Claims claims;

            try {
                claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

            } catch (ExpiredJwtException e) { //만료된 토큰인지 확인

                claims = e.getClaims();
            }

            if (claims.getExpiration().toInstant().toEpochMilli() < Instant.now().toEpochMilli()) {

                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(
                        new ObjectMapper().writeValueAsString(
                                ResponseDto.fail("BAD_REQUEST", "Token이 유효햐지 않습니다.")
                        )
                );
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            String subject = claims.getSubject();
            //클레임에서 권한 정보 가져오기
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            UserDetails principal = userDetailsService.loadUserByUsername(subject);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal, jwt, authorities);

            //SecurityContext에 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println("유효한 토큰입니다.");

        }

        filterChain.doFilter(request, response);
    }
    //Token을 사용하기 위해 Request의 Header에서 Token 값을 가져옴 (Authorization 필드에서 토큰을 추출하는 메소드)
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}