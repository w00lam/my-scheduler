package com.woolam.myscheduler.service;

import com.woolam.myscheduler.dto.CommentCreateRequest;
import com.woolam.myscheduler.dto.CommentCreateResponse;
import com.woolam.myscheduler.entity.Comment;
import com.woolam.myscheduler.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>댓글 관리 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 댓글의 생성 및 조회 기능을 제공합니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * <p>새로운 댓글을 생성하고 저장합니다.</p>
     *
     * @param scheduleId 댓글 생성에 필요한 일정 ID
     * @param request    댓글 생성에 필요한 정보(내용, 작성자, 비밀번호)가 담긴 DTO
     * @return 생성된 댓글의 상세 정보(ID, 생성일시 등 포함)가 담긴 DTO
     */
    public CommentCreateResponse createComment(Long scheduleId, CommentCreateRequest request) {
        int count = commentRepository.countByScheduleId(scheduleId);

        if (count >= 10) {
            throw new IllegalStateException("댓글은 최대 10개까지 가능합니다.");
        }

        validateText(request.getContent(), 100, "댓글 내용");
        validateText(request.getAuthor(), "작성자명");
        validateText(request.getPassword(), "비밀번호");

        Comment comment = new Comment(
                scheduleId,
                request.getContent(),
                request.getAuthor(),
                request.getPassword());
        Comment savedComment = commentRepository.save(comment);
        return new CommentCreateResponse(
                savedComment.getId(),
                savedComment.getScheduleId(),
                savedComment.getAuthor(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    private void validateText(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다.");
        }

        String trimmed = value.trim();

        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 필수입니다.");
        }
    }

    private void validateText(String value, int maxLength, String fieldName) {

        validateText(value, fieldName);

        if (value.trim().length() > maxLength) {
            throw new IllegalArgumentException(fieldName + "은(는) " + maxLength + "자 이하입니다.");
        }
    }
}
