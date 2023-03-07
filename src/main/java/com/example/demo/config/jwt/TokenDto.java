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

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private String authority;


    public void setTokenToHeaders(HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + getAccessToken());
        response.addHeader("authority", getAuthority());
        response.addHeader("RefreshToken", getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", getAccessTokenExpiresIn().toString());
    }
}
