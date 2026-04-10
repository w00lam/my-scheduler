package com.woolam.myscheduler.dto.common;

import com.woolam.myscheduler.common.exception.BusinessException;
import com.woolam.myscheduler.common.exception.ErrorCode;
import com.woolam.myscheduler.common.exception.ErrorDetail;

import java.util.List;

public abstract class ValidateRequest {
    public abstract void validate();

    public void validateTextLength(String value, int maxLength, String fieldName) {
        if (value == null || value.length() > maxLength) {
            throw new BusinessException(
                    ErrorCode.REQUIRED_FIELD_MISSING,
                    List.of(ErrorDetail.of(fieldName, fieldName + "은(는) " + maxLength + "자 이하로 입력해야 합니다."))
            );
        }
    }

    public void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new BusinessException(
                    ErrorCode.REQUIRED_FIELD_MISSING,
                    List.of(ErrorDetail.of(fieldName, fieldName + "은(는) 필수 입력값입니다."))
            );
        }
    }
}
