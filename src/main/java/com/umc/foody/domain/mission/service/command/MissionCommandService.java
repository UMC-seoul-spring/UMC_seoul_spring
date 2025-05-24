package com.umc.foody.domain.mission.service.command;

import org.springframework.security.core.userdetails.User;

import com.umc.foody.domain.mission.dto.request.CreateMissionRequestDto;
import com.umc.foody.domain.mission.dto.request.PatchMissionRequestDto;
import com.umc.foody.domain.mission.dto.response.CreateMissionResponseDto;
import com.umc.foody.domain.mission.dto.response.PatchMissionResponseDto;

public interface MissionCommandService {

	CreateMissionResponseDto createMission(User currentUser, CreateMissionRequestDto createMissionRequestDto);

	PatchMissionResponseDto updateMission(User currentUser, PatchMissionRequestDto patchMissionRequestDto,
		Long missionId);
}
