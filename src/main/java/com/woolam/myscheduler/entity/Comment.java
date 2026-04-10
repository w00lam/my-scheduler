package com.woolam.myscheduler.entity;

import com.woolam.myscheduler.dto.comment.CommentCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>댓글 정보를 저장하는 도메인 엔티티 클래스입니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long scheduleId;
    @Column(length = 100, nullable = false)
    private String content;
    private String author;
    private String password;

    public Comment(Long scheduleId, String content, String author, String password) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    /**
     * <p>CommentCreateRequest를 기반으로 Comment 생성</p>
     */
    public static Comment create(Long scheduleId, CommentCreateRequest request) {
        return new Comment(
                scheduleId,
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );
    }
}
