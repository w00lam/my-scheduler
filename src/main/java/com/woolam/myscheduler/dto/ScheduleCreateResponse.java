package com.woolam.myscheduler.dto;

import java.time.LocalDateTime;

/**
 * <p>일정 생성 완료 후 응답 데이터를 담당하는 Record입니다.</p>
 */
public record ScheduleCreateResponse(Long id,
                                     String title,
                                     String description,
                                     String author,
                                     LocalDateTime createdAt,
                                     LocalDateTime modifiedAt
) {
}
