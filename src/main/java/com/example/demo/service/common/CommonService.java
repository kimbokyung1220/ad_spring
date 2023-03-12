package com.example.demo.service.common;

import com.example.demo.config.jwt.TokenProvider;
import com.example.demo.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final TokenProvider tokenProvider;

//    @Transactional
//    public Member validateMember(HttpServletRequest request) {
//        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
//            return null;
//        }
//        return tokenProvider.getMemberFromAuthentication();
//    }
}
