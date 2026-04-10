package com.woolam.myscheduler.controller;

import com.woolam.myscheduler.common.response.CommonApiResponse;
import com.woolam.myscheduler.dto.comment.CommentCreateRequest;
import com.woolam.myscheduler.dto.comment.CommentCreateResponse;
import com.woolam.myscheduler.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>댓글 관련 HTTP 요청을 처리하는 REST 컨트롤러입니다.
 * "/api/schedules/{scheduleId}" 경로로 들어오는 요청을 담당합니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}")
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "등록 성공")
    @PostMapping("/comments")
    public ResponseEntity<CommonApiResponse<CommentCreateResponse>> createComment(@PathVariable Long scheduleId, @RequestBody CommentCreateRequest request) {
        request.validate();
        CommentCreateResponse response = commentService.createComment(scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonApiResponse.success(201, "댓글 생성 성공", response)
                );
    }
}
