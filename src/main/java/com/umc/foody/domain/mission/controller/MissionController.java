package com.umc.foody.domain.mission.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umc.foody.domain.mission.dto.request.CreateMissionRequestDto;
import com.umc.foody.domain.mission.dto.response.CreateMissionResponseDto;
import com.umc.foody.domain.mission.service.command.MissionCommandService;
import com.umc.foody.global.common.base.BaseResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Mission", description = "미션 관련 API")
@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

	private final MissionCommandService missionCommandService;

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
