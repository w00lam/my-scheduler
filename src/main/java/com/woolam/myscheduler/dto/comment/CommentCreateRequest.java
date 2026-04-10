package com.woolam.myscheduler.dto.comment;

import com.woolam.myscheduler.dto.common.ValidateRequest;
import lombok.Getter;

/**
 * <p>댓글 생성 요청 데이터를 담당하는 DTO 클래스입니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
@Getter
public class CommentCreateRequest extends ValidateRequest {
    private String content;
    private String author;
    private String password;

    @Override
    public void validate() {
        this.validateTextLength(content, 100, "content");
        this.validateText(author, "author");
        this.validateText(password, "password");
    }
}
