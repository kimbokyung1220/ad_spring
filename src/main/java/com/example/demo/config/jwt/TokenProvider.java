package com.example.demo.config.jwt;

import com.example.demo.controller.response.ResponseDto;
import com.example.demo.entity.Member;
import com.example.demo.entity.RefreshToken;
import com.example.demo.service.common.UserDetailsImpl;
import com.example.demo.entity.enm.RoleGroup;
import com.example.demo.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24; // 우선 1일설정, access 재발급 이용가능하면 30분으로 수정예정
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //기준: (1000 -> 1s) // Refresh Token 7일
    private final Key key;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenProvider(@Value("${jwt.secret}") String secretKey, RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        // Key 만들기
        // 로직 안에서는 byte 단위의 secretKey 를 만들어 주어야 한다. (BASE64로 암호화)
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // 알고리즘 선택
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //jwt Token 생성 메서드
    public TokenDto generateTokenDto(Member member) {

        deleteRefreshToken(member);

        long now = (new Date().getTime());

        //Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME); //Access Token 만료시간
        //토큰을 만드는데 Payload 정보와 만료시간, 알고리즘 종료와 암호화 키를 넣어 암호화 함
        String accessToken = Jwts.builder()
                .setSubject(member.getMemberId()) // payload "sub": "name"
                .claim(AUTHORITIES_KEY, RoleGroup.values().toString()) // payload "auth": "ROLE_USER" //auth,role설정
                .setExpiration(accessTokenExpiresIn) //만료시간 토큰에 담기
                .signWith(key, SignatureAlgorithm.HS256) //사용할 암호화 알고리즘과 signature에 들어갈 secret값 세팅
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME)) //만료시간 토큰에 담기
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        //저장할 refreshToken 객체 build
        RefreshToken refreshTokenObject = RefreshToken.builder()
                .member(member)
                .value(refreshToken)
                .build();

        refreshTokenRepository.save(refreshTokenObject);

        return TokenDto.builder()
                .memberId(member.getMemberId())
                .grantType(BEARER_PREFIX)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .authority(member.getRoleGroup().toString())
                .build();

    }

    //토큰으로부터 유저 정보 추출 (토큰으로부터 받은 정보를 기반으로 Authentication 객체를 반환하는 메서드)

    //SecurityContext에 유저 정보가 저장되는 시점
    public Member getMemberFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return null;
        }
        //authentication은 principal을 extends 받은 객체. getMember() 메서드를 통해 사용자의 이름을 넘겨줌
        return ((UserDetailsImpl) authentication.getPrincipal()).getMember();
    }

    //토큰 유효성 확인
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }
    @Transactional(readOnly = true)
    public RefreshToken isPresentRefreshToken(Member member) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByMember(member);
        return optionalRefreshToken.orElse(null);
    }

    @Transactional
    public ResponseDto<?> deleteRefreshToken(Member member) {
        RefreshToken refreshToken = isPresentRefreshToken(member);
        if (null == refreshToken) {
            return ResponseDto.fail("TOKEN_NOT_FOUND", "존재하지 않는 Token 입니다.");
        }

        refreshTokenRepository.delete(refreshToken);
        return ResponseDto.success("success");
    }
}
