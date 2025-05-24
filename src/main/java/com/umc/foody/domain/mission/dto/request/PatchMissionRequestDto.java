package com.umc.foody.domain.mission.dto.request;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class PatchMissionRequestDto {

	private Long missionId;

	private BigDecimal requiredAmount;

	private BigDecimal reward;
	
}
