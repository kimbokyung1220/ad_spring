package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 오류 메시지를 일관된 형식으로 응답하기 위해 공통 ErrorResponse를 생성
 */
@Getter
@ToString
@RequiredArgsConstructor
public class ErrorResponse {
    private final String code;
    private final String desc;
    private HttpStatus status;

    @Builder

    public ErrorResponse(String code, String desc, HttpStatus status) {
        this.code = code;
        this.desc = desc;
        this.status = status;
    }
}