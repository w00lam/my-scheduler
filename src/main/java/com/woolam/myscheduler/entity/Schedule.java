package com.woolam.myscheduler.entity;

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
     * <p>업데이트를 위한 메서드입니다.(Dirty Checking)</p>
     *
     * @param title  수정할 제목입니다.
     * @param author 수정할 작성자입니다.
     */
    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
