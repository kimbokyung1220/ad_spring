package com.example.demo.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String memberId;
    private String grantType; //토큰 타입, 보통 Bearer로 저장됨
    private String accessToken; //인증에 성공하면 발급받는 접근 토큰 정보
    private String refreshToken; //인증에 성공하면 발급받은 새로운 토큰을 발급받기 위한 리프레시 토큰 정보
    private Long accessTokenExpiresIn; //토큰 만료시간 정보
    private String authority;


    public void setTokenToHeaders(HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + getAccessToken());
        response.addHeader("authority", getAuthority());
        response.addHeader("Refresh_Token", getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", getAccessTokenExpiresIn().toString());
    }
}
