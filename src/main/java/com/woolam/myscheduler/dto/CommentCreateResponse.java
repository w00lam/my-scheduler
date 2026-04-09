package com.woolam.myscheduler.dto;

import java.time.LocalDateTime;

/**
 * <p>댓글 생성 완료 후 응답 데이터를 담당하는 Record입니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
public record CommentCreateResponse(Long id,
                                    Long scheduleId,
                                    String content,
                                    String author,
                                    LocalDateTime createdAt,
                                    LocalDateTime updatedAt
) {
}
