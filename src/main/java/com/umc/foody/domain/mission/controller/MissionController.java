package com.umc.foody.domain.mission.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umc.foody.domain.mission.dto.request.CreateMissionRequestDto;
import com.umc.foody.domain.mission.dto.response.CreateMissionResponseDto;
import com.umc.foody.domain.mission.dto.response.GetMissionResponseDto;
import com.umc.foody.domain.mission.enums.MissionSortType;
import com.umc.foody.domain.mission.service.command.MissionCommandService;
import com.umc.foody.domain.mission.service.query.MissionQueryService;
import com.umc.foody.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

	private final MissionCommandService missionCommandService;
	private final MissionQueryService missionQueryService;

	@Operation(summary = "내 주변 미션 조회", description = "현재 위치 기준 같은 지역의 미션들을 조회합니다.")
	@GetMapping("/nearby")
	public BaseResponse<Slice<GetMissionResponseDto>> getNearbyMissions(
		@AuthenticationPrincipal User user,
		@Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
		@RequestParam(defaultValue = "0") int page,
		@Parameter(description = "페이지 크기", example = "10")
		@RequestParam(defaultValue = "10") int size,
		@Parameter(description = "정렬 방식 (DISTANCE: 거리순, LATEST: 최신순, OLDEST: 오래된순)",
			example = "DISTANCE")
		@RequestParam(defaultValue = "DISTANCE") MissionSortType sortType) {

		Pageable pageable = PageRequest.of(page, size);

		Slice<GetMissionResponseDto> missions = missionQueryService.getMissionsNearBy(user, sortType, pageable);

		return BaseResponse.onSuccess(missions);
	}

	@PostMapping
	public BaseResponse<CreateMissionResponseDto> createMission(
		@RequestBody CreateMissionRequestDto request,
		@AuthenticationPrincipal User currentUser
	) {
		return BaseResponse.onSuccess(
			missionCommandService.createMission(currentUser, request)
		);
	}
}
