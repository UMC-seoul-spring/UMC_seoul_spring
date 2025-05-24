package com.umc.foody.domain.mission.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionSortType {
	DISTANCE("거리순"),
	LATEST("최신순"),
	OLDEST("오래된순");

	private final String description;
}