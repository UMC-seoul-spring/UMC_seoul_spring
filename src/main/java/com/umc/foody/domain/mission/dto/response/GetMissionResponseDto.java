package com.umc.foody.domain.mission.dto.response;

import java.math.BigDecimal;

import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.restaurant.entity.Restaurant;
import com.umc.foody.domain.restaurant.enums.RestaurantType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "미션 조회 응답 DTO")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GetMissionResponseDto {

	@Schema(description = "미션 ID", example = "1")
	private Long missionId;

	@Schema(description = "미션 보상 포인트", example = "500")
	private BigDecimal reward;

	@Schema(description = "최소 결제 금액", example = "20000.00")
	private BigDecimal requiredAmount;

	@Schema(description = "레스토랑 ID", example = "100")
	private Long restaurantId;

	@Schema(description = "레스토랑 이름", example = "맛있는 식당")
	private String restaurantName;

	@Schema(description = "레스토랑 유형", example = "한식당")
	private RestaurantType restaurantType;

	@Schema(description = "레스토랑과의 거리(km)", example = "0.5")
	private Double distanceKm;

	public static GetMissionResponseDto of(Mission mission, Restaurant restaurant, Double distanceKm) {
		return GetMissionResponseDto.builder()
			.missionId(mission.getId())
			.reward(mission.getReward())
			.requiredAmount(mission.getRequiredAmount())
			.restaurantId(restaurant.getId())
			.restaurantName(restaurant.getName())
			.restaurantType(restaurant.getRestaurantType())
			.distanceKm(distanceKm)
			.build();
	}
}