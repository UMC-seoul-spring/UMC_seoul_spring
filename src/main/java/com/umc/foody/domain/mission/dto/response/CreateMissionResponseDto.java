package com.umc.foody.domain.mission.dto.response;

import java.math.BigDecimal;

import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.restaurant.entity.Restaurant;
import com.umc.foody.domain.restaurant.enums.RestaurantType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "미션 생성 응답 DTO")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateMissionResponseDto {

	@Schema(description = "생성된 미션 ID", example = "1")
	private Long missionId;

	@Schema(description = "연결된 레스토랑 ID", example = "100")
	private Long restaurantId;

	@Schema(description = "레스토랑 이름", example = "맛있는 식당")
	private String restaurantName;

	@Schema(description = "레스토랑 유형", example = "한식당", enumAsRef = true)
	private RestaurantType restaurantType;

	@Schema(description = "미션 최소 결제 금액", example = "10000.00")
	private BigDecimal requiredAmount;

	@Schema(description = "미션 보상 포인트", example = "500")
	private BigDecimal reward;

	/**
	 * Mission과 Restaurant 엔티티로부터 DTO를 생성하는 팩토리 메서드
	 *
	 * @param mission 미션 엔티티
	 * @param restaurant 레스토랑 엔티티
	 * @return CreateMissionResponseDto 인스턴스
	 */
	public static CreateMissionResponseDto from(Mission mission, Restaurant restaurant) {
		return CreateMissionResponseDto.builder()
			.missionId(mission.getId())
			.restaurantId(restaurant.getId())
			.restaurantName(restaurant.getName())
			.restaurantType(restaurant.getRestaurantType())
			.requiredAmount(mission.getRequiredAmount())
			.reward(mission.getReward())
			.build();
	}

	/**
	 * 개별 필드값으로부터 DTO를 생성하는 팩토리 메서드
	 *
	 * @param missionId 미션 ID
	 * @param restaurantId 레스토랑 ID
	 * @param restaurantName 레스토랑 이름
	 * @param restaurantType 레스토랑 유형
	 * @param requiredAmount 미션 최소 결제 금액
	 * @param reward 미션 보상 포인트
	 * @return CreateMissionResponseDto 인스턴스
	 */
	public static CreateMissionResponseDto of(
		Long missionId,
		Long restaurantId,
		String restaurantName,
		RestaurantType restaurantType,
		BigDecimal requiredAmount,
		BigDecimal reward) {

		return CreateMissionResponseDto.builder()
			.missionId(missionId)
			.restaurantId(restaurantId)
			.restaurantName(restaurantName)
			.restaurantType(restaurantType)
			.requiredAmount(requiredAmount)
			.reward(reward)
			.build();
	}
}