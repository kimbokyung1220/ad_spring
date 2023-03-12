package com.example.demo.config.jwt;

import com.example.demo.controller.response.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//인증된 사용자가 인가되지 않은 페이지에 접근했을 경우 처리할 동작을 정의
public class AccessDeniedHandlerException implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(
                new ObjectMapper().writeValueAsString(
                        ResponseDto.fail("BAD_REQUEST", "권한 오류가 발생하였습니다.")
                )
        );
        //필요한 권한이 없이 접근하려 할때 403
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}