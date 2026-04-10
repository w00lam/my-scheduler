package com.woolam.myscheduler.common.exception;

import lombok.Getter;

/**
 * <p>애플리케이션 전반에서 사용하는 에러 코드를 정의하는 Enum입니다.</p>
 *
 * <p>
 * 각 에러 코드는 HTTP 상태 코드(status), 에러 코드(code), 기본 메시지(message)를 포함합니다.
 * Service 계층에서 BusinessException과 함께 사용되어 일관된 예외 처리를 가능하게 합니다.
 * </p>
 *
 * @author woolam
 * @since 2026-04-10
 */
@Getter
public enum ErrorCode {

    /**
     * <p>필수 값이 누락된 경우 발생하는 에러입니다.</p>
     */
    REQUIRED_FIELD_MISSING(400, "REQUIRED_FIELD_MISSING", "필수 값이 누락되었습니다"),

    /**
     * <p>입력 값이 허용 범위를 초과한 경우 발생하는 에러입니다.</p>
     */
    OUT_OF_RANGE(400, "OUT_OF_RANGE", "허용 범위를 벗어났습니다"),

    /**
     * <p>댓글 개수가 최대 허용 개수를 초과한 경우 발생하는 에러입니다.</p>
     */
    COMMENT_LIMIT_EXCEEDED(400, "COMMENT_LIMIT_EXCEEDED", "댓글 개수 제한을 초과했습니다"),

    /**
     * <p>비밀번호가 일치하지 않는 경우 발생하는 에러입니다.</p>
     */
    INVALID_PASSWORD(401, "INVALID_PASSWORD", "비밀번호가 일치하지 않습니다"),

    /**
     * <p>요청한 일정이 존재하지 않는 경우 발생하는 에러입니다.</p>
     */
    SCHEDULE_NOT_FOUND(404, "SCHEDULE_NOT_FOUND", "해당 일정이 존재하지 않습니다"),

    /**
     * <p>서버 내부 오류가 발생한 경우 사용하는 기본 에러입니다.</p>
     */
    INTERNAL_ERROR(500, "INTERNAL_ERROR", "서버 오류가 발생했습니다");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}