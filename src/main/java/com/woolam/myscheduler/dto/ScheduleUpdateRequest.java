package com.woolam.myscheduler.dto;

import lombok.Getter;

/**
 * <p>일정 수정 요청 데이터를 담당하는 DTO 클래스입니다.</p>
 */
@Getter
public class ScheduleUpdateRequest {
    private String title;
    private String author;
    private String password;
}
