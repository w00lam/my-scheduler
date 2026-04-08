package com.woolam.myscheduler.dto;

import lombok.Getter;

/**
 * <p>일정 목록 조회 시 필터링 조건을 담당하는 DTO 클래스입니다.</p>
 */
@Getter
public class ScheduleGetRequest {
    private String author;
}
