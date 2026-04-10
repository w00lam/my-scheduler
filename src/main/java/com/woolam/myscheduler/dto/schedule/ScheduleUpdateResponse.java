package com.woolam.myscheduler.dto.schedule;

import com.woolam.myscheduler.entity.Schedule;

import java.time.LocalDateTime;

/**
 * <p>일정 수정 결과 데이터를 담당하는 DTO 클래스입니다.</p>
 */
public record ScheduleUpdateResponse(Long id,
                                     String title,
                                     String description,
                                     String author,
                                     LocalDateTime createdAt,
                                     LocalDateTime modifiedAt
) {
    public static ScheduleUpdateResponse from(Schedule schedule) {
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
