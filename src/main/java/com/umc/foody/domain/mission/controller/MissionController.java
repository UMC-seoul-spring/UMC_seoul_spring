package com.umc.foody.domain.mission.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umc.foody.domain.mission.dto.request.CreateMissionRequestDto;
import com.umc.foody.domain.mission.dto.request.PatchMissionRequestDto;
import com.umc.foody.domain.mission.dto.response.CreateMissionResponseDto;
import com.umc.foody.domain.mission.dto.response.GetMissionResponseDto;
import com.umc.foody.domain.mission.dto.response.PatchMissionResponseDto;
import com.umc.foody.domain.mission.enums.MissionSortType;
import com.umc.foody.domain.mission.service.command.MissionCommandService;
import com.umc.foody.domain.mission.service.query.MissionQueryService;
import com.umc.foody.global.common.base.BaseResponse;
import com.umc.foody.global.swagger.CommonParameters;
import com.umc.foody.global.swagger.MissionApiDocs;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Mission", description = "미션 관련 API - 사용자들이 참여할 수 있는 미션을 생성, 조회, 수정, 삭제하는 기능을 제공합니다.")
@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class MissionController {

	private final MissionCommandService missionCommandService;
	private final MissionQueryService missionQueryService;

	@MissionApiDocs.GetNearbyMissions
	@GetMapping("/nearby")
	public BaseResponse<Slice<GetMissionResponseDto>> getNearbyMissions(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@CommonParameters.PageNumber @RequestParam(defaultValue = "0") int page,
		@CommonParameters.PageSize @RequestParam(defaultValue = "10") int size,
		@MissionApiDocs.SortType @RequestParam(defaultValue = "DISTANCE") MissionSortType sortType) {

		Pageable pageable = PageRequest.of(page, size);
		Slice<GetMissionResponseDto> missions = missionQueryService.getMissionsNearBy(user, sortType, pageable);
		return BaseResponse.onSuccess(missions);
	}

	@MissionApiDocs.UpdateMission
	@PatchMapping("/{mission-id}")
	public BaseResponse<PatchMissionResponseDto> patchMission(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@Valid @RequestBody PatchMissionRequestDto request,
		@CommonParameters.ResourceId @PathVariable("mission-id") Long missionId
	) {
		return BaseResponse.onSuccess(missionCommandService.updateMission(user, request, missionId));
	}

	@MissionApiDocs.DeleteMission
	@DeleteMapping("/{mission-id}")
	public BaseResponse<Void> deleteMission(
		@Parameter(hidden = true) @AuthenticationPrincipal User user,
		@CommonParameters.ResourceId @PathVariable("mission-id") Long missionId
	) {
		return BaseResponse.onSuccess(missionCommandService.deleteMission(user, missionId));
	}

	@MissionApiDocs.CreateMission
	@PostMapping
	public BaseResponse<CreateMissionResponseDto> createMission(
		@Valid @RequestBody CreateMissionRequestDto request,
		@Parameter(hidden = true) @AuthenticationPrincipal User currentUser
	) {
		return BaseResponse.onSuccess(
			missionCommandService.createMission(currentUser, request)
		);
	}
}