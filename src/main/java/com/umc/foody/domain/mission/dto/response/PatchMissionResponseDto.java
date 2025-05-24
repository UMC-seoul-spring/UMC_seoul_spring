package com.umc.foody.domain.mission.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.umc.foody.domain.mission.entity.Mission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "미션 수정 응답 DTO")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PatchMissionResponseDto {

	@Schema(description = "미션 ID", example = "1")
	private Long missionId;

	@Schema(description = "보상 포인트", example = "500")
	private BigDecimal reward;

	@Schema(description = "최소 결제 금액", example = "30000.00")
	private BigDecimal requiredAmount;

	@Schema(description = "레스토랑 ID", example = "100")
	private Long restaurantId;

	@Schema(description = "레스토랑 이름", example = "맛있는 식당")
	private String restaurantName;

	@Schema(description = "수정 일시")
	private LocalDateTime updatedAt;

	/**
	 * Mission 엔티티로부터 DTO를 생성하는 팩토리 메서드
	 *
	 * @param mission 미션 엔티티
	 * @return PatchMissionResponseDto 인스턴스
	 */
	public static PatchMissionResponseDto from(Mission mission) {
		return PatchMissionResponseDto.builder()
			.missionId(mission.getId())
			.reward(mission.getReward())
			.requiredAmount(mission.getRequiredAmount())
			.restaurantId(mission.getRestaurant().getId())
			.restaurantName(mission.getRestaurant().getName())
			.updatedAt(mission.getUpdatedAt())
			.build();
	}
}