package com.woolam.myscheduler.dto;

import java.time.LocalDateTime;

/**
 * <p>일정 조회 결과 데이터를 담당하는 Record입니다.</p>
 */
public record ScheduleGetResponse(Long id,
                                  String title,
                                  String description,
                                  String author,
                                  LocalDateTime createdAt,
                                  LocalDateTime modifiedAt
) {
}
