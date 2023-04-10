package com.example.demo.exception;

import com.example.demo.entity.TaskReq;
import com.example.demo.entity.enm.TaskStatus;
import com.example.demo.repository.TaskReqRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

/**
 *  [ Exception handler 구현 : 전역 예외처리 ]
 * @RestControllerAdvice ( @ControllerAdvice [@Component 포함] + @ResponseBody )
 *  - 전역적으로 에러를 핸들링하는 클래스를 만들어 어노테이션을 붙여줌으로써 에러 처리를 위임할 수 있음
 *  - @ResponseBody가 붙어 있어 응답을 Json으로 내려줌
 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomExceptionHandler {
    private final TaskReqRepository taskReqRepository;

    /**
     * @ExceptionHandler: 특정 클래스에서 발생할 수 있는 예외를 잡아 Throw
     */
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final CustomException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .code(e.getErrorCode().getCode())
                        .desc(e.getErrorCode().getMessage())
                        .status(e.getErrorCode().getStatus())
                        .keyword(e.getData())
                        .build());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final InvalidFormatException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ErrorCode.INVALID_PARAMETER.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INVALID_PARAMETER.getCode())
                        .desc(ErrorCode.INVALID_PARAMETER.getMessage())
                        .status(ErrorCode.INVALID_PARAMETER.getStatus())
                        .build());
    }

    // [Exception] 해당 데이터가 없을 때
    @ExceptionHandler(NoSuchElementException.class)
    public Object processValidationError(NoSuchElementException e) {
        return ResponseEntity
                .status(ErrorCode.NO_SEARCH_ELEMENT.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.NO_SEARCH_ELEMENT.getCode())
                        .desc(e.getMessage() + "ddd")
                        .build());
    }


    // [Exception] API 호출 시 '객체' 혹은 '파라미터' 데이터 값이 유효하지 않은 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ErrorCode.TYPE_NULL_ERROR.getStatus())
                        .body(ErrorResponse.builder()
                                .code(ErrorCode.TYPE_NULL_ERROR.getCode())
                                .desc(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                                .build());
    }

    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
    public Object processValidationError(MethodArgumentConversionNotSupportedException e) {
        return ResponseEntity
                .status(ErrorCode.TYPE_NULL_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.TYPE_NULL_ERROR.getCode())
                        .desc(ErrorCode.TYPE_NULL_ERROR.getMessage())
                        .build());
    }

    // [Exception] 클라이언트에서 Body로 '객체' 데이터가 넘어오지 않았을 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object processValidationError(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(ErrorCode.TYPE_NULL_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.TYPE_NULL_ERROR.getCode())
                        .desc(ErrorCode.TYPE_NULL_ERROR.getMessage())
                        .build());
    }

    // [Exception] 클라이언트에서 잘못된 메서드로 요청했을 경우
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object processValidationError(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity
                .status(ErrorCode.METHOD_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.METHOD_ERROR.getCode())
                        .desc(ErrorCode.METHOD_ERROR.getMessage())
                        .status(ErrorCode.METHOD_ERROR.getStatus())
                        .build());
    }


    // [Exception] 클라이언트에서 잘못된 메서드로 요청했을 경우
    @ExceptionHandler(UsernameNotFoundException.class)
    public Object processValidationError(UsernameNotFoundException e) {
        return ResponseEntity
                .status(ErrorCode.USER_NAME_NOT_FOUND.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.USER_NAME_NOT_FOUND.getCode())
                        .desc(ErrorCode.USER_NAME_NOT_FOUND.getMessage())
                        .status(ErrorCode.USER_NAME_NOT_FOUND.getStatus())
                        .build());
    }

    // [Exception] 잘못된 서버 요청일 경우 발생한 경우
    @ExceptionHandler(HttpClientErrorException.class)
    public Object processValidationError(HttpClientErrorException e) {
        return ResponseEntity
                .status(ErrorCode.HTTP_CT_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.HTTP_CT_ERROR.getCode())
                        .desc(e.getMessage())
                        .status(ErrorCode.HTTP_CT_ERROR.getStatus())
                        .build());
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Object processValidationError(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity
                .status(ErrorCode.HTTP_CT_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.HTTP_CT_ERROR.getCode())
                        .desc(e.getMessage())
                        .status(ErrorCode.HTTP_CT_ERROR.getStatus())
                        .build());
    }
    // 대량관리 - 작업요청 등록 시, 첨부파일이 없을 경우
    @ExceptionHandler(MissingServletRequestPartException.class)
    public Object processValidationError(MissingServletRequestPartException e) {
        return ResponseEntity
                .status(ErrorCode.EMPTY_TASK_FILE.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.EMPTY_TASK_FILE.getCode())
                        .desc(ErrorCode.EMPTY_TASK_FILE.getMessage())
                        .status(ErrorCode.EMPTY_TASK_FILE.getStatus())
                        .build());
    }
}
