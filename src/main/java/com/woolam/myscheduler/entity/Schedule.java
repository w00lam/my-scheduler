package com.woolam.myscheduler.entity;

import com.woolam.myscheduler.common.exception.BusinessException;
import com.woolam.myscheduler.common.exception.ErrorCode;
import com.woolam.myscheduler.dto.schedule.ScheduleCreateRequest;
import com.woolam.myscheduler.dto.schedule.ScheduleUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * <p>일정 정보를 저장하는 도메인 엔티티 클래스입니다.</p>
 */
@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String description;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;

    public Schedule(String title, String description, String author, String password) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.password = password;
    }

    /**
     * <p>ScheduleCreateRequest 기반으로 Schedule 생성</p>
     */
    public static Schedule create(ScheduleCreateRequest request) {
        return new Schedule(
                request.getTitle(),
                request.getDescription(),
                request.getAuthor(),
                request.getPassword()
        );
    }

    /**
     * <p>업데이트를 위한 메서드입니다.(Dirty Checking)</p>
     *
     * @param request 수정할 제목, 작성자입니다.
     */
    public void checkPasswordAndUpdate(ScheduleUpdateRequest request) {
        validatePassword(request.getPassword());
        this.title = request.getTitle();
        this.author = request.getAuthor();
    }

    public void validatePassword(String password) {
        if (!this.password.equals(password)) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
