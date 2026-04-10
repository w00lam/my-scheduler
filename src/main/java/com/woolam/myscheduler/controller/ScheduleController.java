package com.woolam.myscheduler.controller;

import com.woolam.myscheduler.common.response.CommonApiResponse;
import com.woolam.myscheduler.dto.schedule.*;
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
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "필수 값 누락")
    })
    @PostMapping
    public ResponseEntity<CommonApiResponse<ScheduleCreateResponse>> createSchedule(
            @RequestBody ScheduleCreateRequest request) {
        request.validate();

        ScheduleCreateResponse response = scheduleService.createSchedule(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonApiResponse.success(201, "일정 생성 성공", response)
                );
    }

    @Operation(
            summary = "일정 목록 조회",
            description = "작성자명을 기준으로 등록된 일정을 수정일 기준 내림차순으로 정렬합니다. " +
                    "작성자명은 조회 조건으로 포함될 수도 있고, 포함되지 않을 수도 있습니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<CommonApiResponse<List<ScheduleGetAllResponse>>> getAll(@RequestParam(required = false) String author) {
        if (author == null || author.isBlank()) {
            return ResponseEntity.ok(
                    CommonApiResponse.success(
                            200, "조회 성공", scheduleService.getSchedules()
                    )
            );
        } else {
            return ResponseEntity.ok(
                    CommonApiResponse.success(
                            200, "조회 성공", scheduleService.getSchedulesByAuthor(author)
                    )
            );
        }
    }

    @Operation(summary = "해당 일정 조회", description = "해당 일정 ID로 단건 조회합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 일정을 찾을 수 없음")
    })
    @GetMapping("/{scheduleId}")
    public ResponseEntity<CommonApiResponse<ScheduleGetOneResponse>> getOne(
            @Parameter(description = "조회할 일정 ID", example = "1", required = true)
            @PathVariable Long scheduleId) {
        ScheduleGetOneResponse response = scheduleService.getSchedule(scheduleId);

        return ResponseEntity.ok(
                CommonApiResponse.success(200, "조회 성공", response)
        );
    }

    @Operation(summary = "일정 수정", description = "일정을 수정합니다 작성한 일정의 ID, 작성자, 비밀번호를 받습니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "필수 값 누락"),
            @ApiResponse(responseCode = "401", description = "비밀번호 불일치"),
            @ApiResponse(responseCode = "404", description = "해당 일정을 찾을 수 없음")
    })
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<CommonApiResponse<ScheduleUpdateResponse>> update(
            @Parameter(description = "수정할 일정 ID", example = "1", required = true)
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request) {
        request.validate();

        ScheduleUpdateResponse response = scheduleService.update(scheduleId, request);

        return ResponseEntity.ok(
                CommonApiResponse.success(200, "수정 성공", response)
        );
    }

    @Operation(summary = "일정 삭제", description = "일정을 삭제합니다 작성한 일정의 ID, 비밀번호를 받습니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "401", description = "비밀번호 불일치"),
            @ApiResponse(responseCode = "404", description = "해당 일정을 찾을 수 없음")
    })
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<CommonApiResponse<Void>> delete(
            @Parameter(description = "삭제할 일정 ID", example = "1", required = true)
            @PathVariable Long scheduleId,
            @RequestParam String password) {

        scheduleService.delete(scheduleId, password);

        return ResponseEntity.ok(
                CommonApiResponse.success(200, "삭제 성공", null)
        );
    }
}
