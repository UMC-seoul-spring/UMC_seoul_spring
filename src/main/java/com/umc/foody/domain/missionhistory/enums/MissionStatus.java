package com.umc.foody.domain.missionhistory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionStatus {

	IN_PROGRESS("진행 중인 미션"),
	COMPLETED("완료한 미션");

	private final String description;

}
