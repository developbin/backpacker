package com.backpacker.homework.code;

import lombok.ToString;

@ToString
public enum ApiResponseCode {

    SUCCESS                     ("2000", "OK"),
    SYSTEM_ERROR                ("1001", "시스템 오류"),
    SYSTEM_PERMISSION_ERROR     ("1002", "시스템 권한 오류"),
    SYSTEM_STATUS_ERROR         ("1003", "시스템 상태 이상"),
    BAD_REQUEST_ERROR           ("9000", "부적절한 요청 오류"),
    RESOURCE_NOT_FOUND          ("4004", "해당 리소스가 없음"),
    UNAUTHORIZED_ERROR          ("9001", "인증 오류"),
    UNKNOWN_ERROR               ("9999", "알 수 없는 오류");

    private final String code;

    private final String defaultMessage;

    ApiResponseCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return this.code;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

}
