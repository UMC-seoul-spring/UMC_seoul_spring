package com.umc.foody.domain.mission.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umc.foody.domain.mission.dto.request.CreateMissionRequestDto;
import com.umc.foody.domain.mission.dto.response.CreateMissionResponseDto;
import com.umc.foody.domain.mission.dto.response.GetMissionResponseDto;
import com.umc.foody.domain.mission.service.command.MissionCommandService;
import com.umc.foody.domain.mission.service.query.MissionQueryService;
import com.umc.foody.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

	private final MissionCommandService missionCommandService;
	private final MissionQueryService missionQueryService;

	@GetMapping("/nearby")
	public BaseResponse<Slice<GetMissionResponseDto>> getNearbyMissions(
		@AuthenticationPrincipal User user,
		@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
		Pageable pageable) {

		return BaseResponse.onSuccess(missionQueryService.getMissionsNearBy(user, pageable));
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
