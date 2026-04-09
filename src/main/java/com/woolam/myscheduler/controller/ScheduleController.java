package com.woolam.myscheduler.controller;

import com.woolam.myscheduler.dto.*;
import com.woolam.myscheduler.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>일정 관련 HTTP 요청을 처리하는 REST 컨트롤러입니다.
 * "/api/schedules" 경로로 들어오는 요청을 담당합니다.</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Operation(summary = "일정 생성", description = "일정을 생성합니다.")
    @ApiResponse(responseCode = "201", description = "등록 성공")
    @PostMapping
    public ResponseEntity<ScheduleCreateResponse> createSchedule(@RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        ScheduleCreateResponse response = scheduleService.createSchedule(scheduleCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "일정 목록 조회",
            description = "작성자명을 기준으로 등록된 일정을 수정일 기준 내림차순으로 정렬합니다. 작성자명은 조회 조건으로 포함될 수도 있고, 포함되지 않을 수도 있습니다.")
    @ApiResponse(responseCode = "200",description = "조회 성공")
    @GetMapping
    public ResponseEntity<List<ScheduleGetAllResponse>> getAll(@RequestBody ScheduleGetRequest request) {
        List<ScheduleGetAllResponse> response = scheduleService.getSchedules(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "해당 일정 조회",description = "해당 일정 ID로 단건 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "조회 성공"),
            @ApiResponse(responseCode = "404",description = "해당 일정을 찾을 수 없음")
    })
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponse> getOne(
            @Parameter(description = "조회할 일정 ID",example = "1",required = true)
            @PathVariable Long scheduleId) {
        ScheduleGetOneResponse response = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "일정 수정",description = "일정을 수정합니다 작성한 일정의 ID, 작성자, 비밀번호를 받습니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "수정 성공"),
            @ApiResponse(responseCode = "404",description = "해당 일정을 찾을 수 없음")
    })
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @Parameter(description = "수정할 일정 ID",example = "1",required = true)
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request) {
        ScheduleUpdateResponse response = scheduleService.update(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "일정 삭제",description = "일정을 삭제합니다 작성한 일정의 ID, 비밀번호를 받습니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "삭제 성공"),
            @ApiResponse(responseCode = "404",description = "해당 일정을 찾을 수 없음")
    })
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "삭제할 일정 ID",example = "1",required = true)
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDeleteRequest request) {
        scheduleService.delete(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
