package com.woolam.myscheduler.common.exception;

import lombok.Getter;

/**
 * <p>필드 단위의 상세 에러 정보를 담는 클래스입니다.</p>
 *
 * <p>
 * 어떤 필드에서 어떤 문제가 발생했는지를 표현하며
 * 주로 Validation 에러에서 사용됩니다.
 * </p>
 *
 * @author woolam
 * @since 2026-04-10
 */
@Getter
public class ErrorDetail {
    private final String field;
    private final String message;

    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static ErrorDetail of(String field, String message) {
        return new ErrorDetail(field, message);
    }
}