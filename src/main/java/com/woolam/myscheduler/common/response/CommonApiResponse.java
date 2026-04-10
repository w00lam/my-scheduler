package com.woolam.myscheduler.common.response;

import lombok.Getter;

/**
 * <p>API 응답을 통일하기 위한 공통 응답 클래스입니다.</p>
 *
 * <p>
 * status(HTTP 상태 코드), message(응답 메시지), data(응답 데이터)를 포함합니다.
 * </p>
 *
 * @author woolam
 * @since 2026-04-10
 */
@Getter
public class CommonApiResponse<T> {

    private final int status;
    private final String message;
    private final T data;

    private CommonApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * <p>성공 응답을 생성합니다.</p>
     */
    public static <T> CommonApiResponse<T> success(int status, String message, T data) {
        return new CommonApiResponse<>(status, message, data);
    }

    /**
     * <p>실패 응답을 생성합니다.</p>
     */
    public static <T> CommonApiResponse<T> error(int status, String message, T data) {
        return new CommonApiResponse<>(status, message, data);
    }

}