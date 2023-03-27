package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 모든 예외를 관리할 ErrorCode
 */
@Getter
public enum ErrorCode {
    // Common Error
    INVALID_PARAMETER("CER-01", HttpStatus.BAD_REQUEST, "잘못된 값입니다."),
    RESOURCE_NOT_FOUND("CER-02", HttpStatus.NOT_FOUND, "존재하지 않는 Resource입니다."),
    INTERNAL_SERVER_ERROR("CER-03", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error 입니다."),
    METHOD_ERROR("CER-04", HttpStatus.INTERNAL_SERVER_ERROR, "잘못된 메서드 요청입니다."),
    TYPE_NULL_ERROR("CER-05", HttpStatus.BAD_REQUEST,"모든 값을 입력해 주세요."),
    HTTP_CT_ERROR("CER-06", HttpStatus.INTERNAL_SERVER_ERROR, "잘못된 서버 요청입니다."),
    SQL_ERROR("CER-06", HttpStatus.INTERNAL_SERVER_ERROR, "SQL을 확인하세요"),

    //사용자 Error
    EXIST_AD_ITEM("EXIST_AD_ITEM", HttpStatus.BAD_REQUEST, "해당 상품은 이미 광고로 등록되어 있습니다."),
    EXIST_AD_GROUP("EXIST_AD_GROUP", HttpStatus.BAD_REQUEST, "해당 광고그룹은 등록되어 있습니다."),
    NO_HAVE_AD_GROUP("NO_HAVE_AD_GROUP", HttpStatus.BAD_REQUEST, "광그그룹명을 입력해 주세요."),
    CHANGE_AD_GROUP("CHANGE_AD_GROUP", HttpStatus.BAD_REQUEST, "광그그룹명을 변경하였습니다."),
    EXIST_AD("EXIST_AD", HttpStatus.BAD_REQUEST, "이미 광고가 등록되어 있습니다."),
    DAY_LIMIT_MEASURE("DAY_LIMIT_MEASURE", HttpStatus.BAD_REQUEST, "일일 허용 예산은 100원 단위로 변경 가능합니다."),
    ADGROUP_ACT_ON("ADGROUP_ACT_ON", HttpStatus.BAD_REQUEST, "광고그룹 사용여부가 ON으로 변경되었습니다."),
    ADGROUP_ACT_OFF("ADGROUP_ACT_OFF", HttpStatus.BAD_REQUEST, "광고그룹 사용여부가 OFF로 변경되었습니다."),
    NOT_SELECTED_ADGROUP("NOT_SELECTED_ADGROUP", HttpStatus.BAD_REQUEST, "선택한 그룹이 없습니다."),
    DELETE_ADGROUP("DELETE_ADGROUP", HttpStatus.BAD_REQUEST, "광고그룹을 삭제했습니다."),
    NOT_FOUND_ID("UER-01", HttpStatus.BAD_REQUEST, "해당 게시글을 찾지 못했습니다."),
    NOT_FOUND_USERID("UER-02", HttpStatus.BAD_REQUEST, "존재하지 않는 아이디입니다."),


    // JWT
    UNAUTHORIZED("JWT-01",HttpStatus.BAD_REQUEST,  "로그인이 필요합니다.")

    ;

    private final String code;
    private HttpStatus status;
    private String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
