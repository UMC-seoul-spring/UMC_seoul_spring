package com.umc.foody.domain.mission.service.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.User;

import com.umc.foody.domain.mission.dto.response.GetMissionResponseDto;
import com.umc.foody.domain.mission.enums.MissionSortType;

public interface MissionQueryService {
	Slice<GetMissionResponseDto> getMissionsNearBy(User user, MissionSortType sortType, Pageable pageable);
}