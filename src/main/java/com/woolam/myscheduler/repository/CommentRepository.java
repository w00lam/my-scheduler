package com.woolam.myscheduler.repository;

import com.woolam.myscheduler.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>Comment 엔티티에 대한 데이터베이스 액세스를 담당하는 리포지토리 인터페이스입니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-09
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    int countByScheduleId(Long scheduleId);
    List<Comment> findByScheduleId(Long scheduleId);
}
