package com.woolam.myscheduler.repository;

import com.woolam.myscheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <p>Schedule 엔티티에 대한 데이터베이스 액세스를 담당하는 리포지토리 인터페이스입니다.</p>
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByUpdatedAtDesc();
    List<Schedule> findByAuthorOrderByUpdatedAtDesc(String author);
}
