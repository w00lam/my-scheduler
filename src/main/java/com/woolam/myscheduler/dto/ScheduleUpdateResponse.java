package com.woolam.myscheduler.dto;

import java.time.LocalDateTime;

/**
 * <p>일정 수정 결과 데이터를 담당하는 DTO 클래스입니다.</p>
 */
public record ScheduleUpdateResponse(Long id, String title, String description, String author, LocalDateTime createdAt,
                                     LocalDateTime modifiedAt) {
}
