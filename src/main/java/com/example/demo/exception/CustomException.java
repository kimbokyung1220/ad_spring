package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 의도한 에외상황에 사용하기 위해 공통 Exception을 구현
 * ex) throw new CustomException(ErrorCode.ENUM_CODE);
 */
@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;
    private String data;

    // 예외 발생 원인(예외 메시지)을 전달하기 위해 Error 타입의 매개변수를 갖는 생성자
    public CustomException(ErrorCode e) {
//        super(e.getMessage()); // RuntimeException 클래스의 생성자를 호출
        this.errorCode = e;
    }

    public CustomException(ErrorCode e, String data) {
        this.errorCode = e;
        this.data = data;
    }
}
