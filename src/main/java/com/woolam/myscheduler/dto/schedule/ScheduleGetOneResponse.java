package com.woolam.myscheduler.dto.schedule;

import com.woolam.myscheduler.dto.comment.CommentGetResponse;
import com.woolam.myscheduler.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>일정 단건 조회 결과 데이터를 담당하는 Record입니다.</p>
 */
public record ScheduleGetOneResponse(Long id,
                                     String title,
                                     String description,
                                     String author,
                                     List<CommentGetResponse> comments,
                                     LocalDateTime createdAt,
                                     LocalDateTime modifiedAt
) {
    public static ScheduleGetOneResponse from(Schedule schedule, List<CommentGetResponse> comments) {
        return new ScheduleGetOneResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getAuthor(),
                comments,
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
