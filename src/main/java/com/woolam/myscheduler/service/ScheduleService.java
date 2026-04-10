package com.woolam.myscheduler.service;

import com.woolam.myscheduler.common.exception.BusinessException;
import com.woolam.myscheduler.common.exception.ErrorCode;
import com.woolam.myscheduler.common.exception.ErrorDetail;
import com.woolam.myscheduler.dto.comment.CommentGetResponse;
import com.woolam.myscheduler.dto.schedule.*;
import com.woolam.myscheduler.entity.Comment;
import com.woolam.myscheduler.entity.Schedule;
import com.woolam.myscheduler.repository.CommentRepository;
import com.woolam.myscheduler.repository.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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
    private final CommentRepository commentRepository;

    /**
     * <p>새로운 일정을 생성하고 저장합니다.</p>
     *
     * @param request 일정 생성에 필요한 정보(제목, 내용, 작성자, 비밀번호)가 담긴 DTO
     * @return 생성된 일정의 상세 정보(ID, 생성일시 등 포함)가 담긴 DTO
     */
    @Transactional
    public ScheduleCreateResponse createSchedule(ScheduleCreateRequest request) {
        Schedule schedule = Schedule.create(request);
        scheduleRepository.save(schedule);

        return ScheduleCreateResponse.from(schedule);
    }

    /**
     * <p>일정 목록을 조회합니다.</p>
     *
     * @return 일정 상세 정보 목록
     * @author woolam
     * @since 2026-04-10
     */
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> getSchedules() {
        return scheduleRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(ScheduleGetAllResponse::from)
                .toList();
    }

    /**
     * <p>해당 작성자의 일정만 필터링하여
     * 수정일 기준 내림차순으로 정렬합니다.</p>
     *
     * @param author 작성자명 변수입니다.
     * @return 조건에 맞는 일정 상세 정보 목록
     * @author woolam
     * @since 2026-04-10
     */
    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> getSchedulesByAuthor(String author) {
        return scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author).stream()
                .map(ScheduleGetAllResponse::from)
                .toList();
    }

    /**
     * <p>선택 일정을 조회합니다.
     * 해당 일정에 등록된 댓글도 포함하여 조회합니다.</p>
     *
     * @param scheduleId 일정의 고유 식별자
     * @return 조건에 맞는 일정 상세 정보 목록
     */
    @Transactional(readOnly = true)
    public ScheduleGetOneResponse getSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = this.findScheduleOrThrow(scheduleId);

        List<CommentGetResponse> commentDtos = getComments(scheduleId).stream()
                .map(CommentGetResponse::from)
                .toList();

        return ScheduleGetOneResponse.from(schedule, commentDtos);
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
        schedule.checkPasswordAndUpdate(request);

        return ScheduleUpdateResponse.from(schedule);
    }

    /**
     * <p>선택 일정을 고유 식별자로 삭제합니다.</p>
     *
     * @param scheduleId 일정의 고유 식별자
     * @param password   삭제 요청에 필요한 비밀번호
     */
    @Transactional
    public void delete(Long scheduleId, String password) {
        Schedule schedule = findScheduleOrThrow(scheduleId);
        schedule.validatePassword(password);

        scheduleRepository.deleteById(scheduleId);
    }

    private Schedule findScheduleOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }

    private List<Comment> getComments(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId);
    }
}
