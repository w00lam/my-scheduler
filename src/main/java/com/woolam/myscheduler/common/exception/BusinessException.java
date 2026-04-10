package com.woolam.myscheduler.common.exception;

import lombok.Getter;

import java.util.List;

/**
 * <p>비즈니스 로직에서 발생하는 예외를 처리하기 위한 커스텀 예외 클래스입니다.</p>
 *
 * <p>
 * ErrorCode를 기반으로 예외를 생성하며 필요 시 상세 에러 정보(ErrorDetail)를 함께 전달할 수 있습니다.
 * GlobalExceptionHandler에서 해당 예외를 받아 일관된 API 응답으로 변환합니다.
 * </p>
 *
 * @author woolam
 * @since 2026-04-10
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final List<ErrorDetail> details;

    /**
     * <p>ErrorCode만 사용하는 기본 생성자입니다.</p>
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.details = null;
    }

    /**
     * <p>ErrorCode와 상세 에러 정보를 함께 전달하는 생성자입니다.</p>
     */
    public BusinessException(ErrorCode errorCode, List<ErrorDetail> details) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.details = details;
    }
}