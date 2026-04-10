package com.woolam.myscheduler.dto.schedule;

import com.woolam.myscheduler.entity.Schedule;

import java.time.LocalDateTime;

/**
 * <p>일정 모두 조회 결과 데이터를 담당하는 Record입니다.</p>
 */
public record ScheduleGetAllResponse(Long id,
                                     String title,
                                     String description,
                                     String author,
                                     LocalDateTime createdAt,
                                     LocalDateTime modifiedAt
) {
    public static ScheduleGetAllResponse from(Schedule schedule) {
        return new ScheduleGetAllResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
