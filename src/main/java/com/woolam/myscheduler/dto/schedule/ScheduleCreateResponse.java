package com.woolam.myscheduler.dto.schedule;

import com.woolam.myscheduler.entity.Schedule;

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
    /**
     * <p>Schedule 엔티티를 기반으로 응답 DTO를 생성합니다.</p>
     */
    public static ScheduleCreateResponse from(Schedule schedule) {
        return new ScheduleCreateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
