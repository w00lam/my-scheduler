package com.woolam.myscheduler.common.exception;

import com.woolam.myscheduler.common.response.CommonApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>전역 예외 처리 핸들러입니다.</p>
 *
 * @author woolam
 * @since 2026-04-10
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <p>비즈니스 예외 처리</p>
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonApiResponse<?>> handleBusinessException(BusinessException e) {

        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(CommonApiResponse.error(
                        errorCode.getStatus(),
                        errorCode.getMessage(),
                        e.getDetails()
                ));
    }

    /**
     * <p>예상하지 못한 예외 처리</p>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<?>> handleException(Exception e) {

        return ResponseEntity
                .status(ErrorCode.INTERNAL_ERROR.getStatus())
                .body(CommonApiResponse.error(
                        ErrorCode.INTERNAL_ERROR.getStatus(),
                        ErrorCode.INTERNAL_ERROR.getMessage(),
                        null
                ));
    }
}