package com.woolam.myscheduler.dto.comment;

import com.woolam.myscheduler.entity.Comment;

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
    /**
     * <p>Comment 엔티티를 기반으로 응답 DTO를 생성합니다.</p>
     */
    public static CommentCreateResponse from(Comment comment) {
        return new CommentCreateResponse(
                comment.getId(),
                comment.getScheduleId(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
