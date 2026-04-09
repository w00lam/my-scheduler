package com.woolam.myscheduler.dto;

import lombok.Getter;

/**
 * <p>댓글 생성 요청 데이터를 담당하는 DTO 클래스입니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
@Getter
public class CommentCreateRequest {
    private String content;
    private String author;
    private String password;
}
