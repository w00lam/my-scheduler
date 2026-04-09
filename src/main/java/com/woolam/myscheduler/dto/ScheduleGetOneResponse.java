package com.woolam.myscheduler.dto;

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
}
