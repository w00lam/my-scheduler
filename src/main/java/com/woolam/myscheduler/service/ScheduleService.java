package com.woolam.myscheduler.service;

import com.woolam.myscheduler.dto.*;
import com.woolam.myscheduler.entity.Schedule;
import com.woolam.myscheduler.repository.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>일정 관리 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 일정의 생성 및 조회 기능을 제공합니다.</p>
 *
 * @author woolam
 * @version 1.0
 * @since 2026-04-08
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    /**
     * <p>새로운 일정을 생성하고 저장합니다.</p>
     *
     * @param request 일정 생성에 필요한 정보(제목, 내용, 작성자, 비밀번호)가 담긴 DTO
     * @return 생성된 일정의 상세 정보(ID, 생성일시 등 포함)가 담긴 DTO
     */
    @Transactional
    public ScheduleCreateResponse createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getDescription(),
                request.getAuthor(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getDescription(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    /**
     * <p>일정 목록을 조회합니다.
     * 작성자명이 제공될 경우 해당 작성자의 일정만 필터링하며
     * 수정일 기준 내림차순으로 정렬합니다.</p>
     *
     * @param request 조회 필터 조건(작성자명)이 담긴 DTO
     * @return 조건에 맞는 일정 상세 정보 목록
     */
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> getSchedules(ScheduleGetRequest request) {
        List<Schedule> schedules = Optional.ofNullable(request.getAuthor())
                .filter(author -> !author.isBlank())
                .map(scheduleRepository::findByAuthorOrderByUpdatedAtDesc)
                .orElseGet(scheduleRepository::findAllByOrderByUpdatedAtDesc);
        List<ScheduleGetResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleGetResponse dto = new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getDescription(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    /**
     * <p>선택 일정을 조회합니다.</p>
     *
     * @param scheduleId 일정의 고유 식별자
     * @return 조건에 맞는 일정 상세 정보 목록
     */
    @Transactional(readOnly = true)
    public ScheduleGetResponse getSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = findScheduleOrThrow(scheduleId);

        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    /**
     * <p>선택 일정을 수정합니다.
     * 일정 제목과 작성자명만 수정 가능합니다.</p>
     *
     * @param scheduleId 일정의 고유 식별자
     * @param request    수정 정보가 담긴 dto
     * @return 조건에 맞는 일정 상세 정보 목록
     */
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = findScheduleOrThrow(scheduleId);

        validatePassword(schedule.getPassword(), request.getPassword());

        schedule.update(
                request.getTitle(),
                request.getAuthor()
        );
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    /**
     * <p>선택 일정을 고유 식별자로 삭제합니다.</p>
     *
     * @param scheduleId 일정의 고유 식별자
     * @param request    삭제 요청에 필요한 비밀번호
     */
    @Transactional
    public void delete(Long scheduleId, ScheduleDeleteRequest request) {
        Schedule schedule = findScheduleOrThrow(scheduleId);

        validatePassword(schedule.getPassword(), request.getPassword());

        scheduleRepository.deleteById(scheduleId);
    }

    private Schedule findScheduleOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));
    }

    private void validatePassword(String savedPassword, String inputPassword) {
        if (!savedPassword.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

}
