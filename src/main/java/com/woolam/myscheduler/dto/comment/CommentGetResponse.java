package com.woolam.myscheduler.dto.comment;

import com.woolam.myscheduler.entity.Comment;

import java.time.LocalDateTime;

/**
 * <p>댓글 조회 결과 데이터를 담당하는 Record입니다.</p>
 */
public record CommentGetResponse(Long id,
                                 Long scheduleId,
                                 String author,
                                 String password,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt
) {
    public static CommentGetResponse from(Comment comment) {
        return new CommentGetResponse(
                comment.getId(),
                comment.getScheduleId(),
                comment.getAuthor(),
                comment.getPassword(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
