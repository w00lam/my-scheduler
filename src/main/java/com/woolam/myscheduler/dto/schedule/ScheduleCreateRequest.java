package com.woolam.myscheduler.dto.schedule;

import com.woolam.myscheduler.dto.common.ValidateRequest;
import lombok.Getter;

/**
 * <p>일정 생성 요청 데이터를 담당하는 DTO 클래스입니다.</p>
 */
@Getter
public class ScheduleCreateRequest extends ValidateRequest {
    private String title;
    private String description;
    private String author;
    private String password;

    @Override
    public void validate() {
        this.validateTextLength(title, 30, "title");
        this.validateTextLength(description, 200, "description");
        this.validateText(author, "author");
        this.validateText(password, "password");
    }
}
