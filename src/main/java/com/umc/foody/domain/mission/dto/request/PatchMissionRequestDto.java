package com.umc.foody.domain.mission.dto.request;

import java.math.BigDecimal;

import com.umc.foody.domain.mission.exception.annotation.ValidPatchRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ValidPatchRequest // 커스텀 검증 적용
@Schema(description = "미션 수정 요청 DTO")
public class PatchMissionRequestDto {

	@DecimalMin(value = "0", inclusive = true, message = "보상 포인트는 0 이상이어야 합니다")
	@Digits(integer = 10, fraction = 0, message = "보상 포인트는 정수 10자리 이하여야 합니다")
	@Schema(description = "보상 포인트 (선택적)", example = "500", required = false)
	private BigDecimal reward;

	@DecimalMin(value = "0", inclusive = true, message = "최소 결제 금액은 0 이상이어야 합니다")
	@Digits(integer = 10, fraction = 2, message = "최소 결제 금액은 소수점 2자리까지 허용합니다")
	@Schema(description = "최소 결제 금액 (선택적)", example = "10000.00", required = false)
	private BigDecimal requiredAmount;

	// 헬퍼 메서드들 (위에서 정의한 것과 동일)
	public boolean hasReward() {
		return reward != null;
	}

	public boolean hasRequiredAmount() {
		return requiredAmount != null;
	}

	public boolean hasAnyUpdateField() {
		return hasReward() || hasRequiredAmount();
	}

	public boolean isValidUpdateRequest() {
		if (!hasAnyUpdateField()) {
			return false;
		}

		if (hasReward() && (reward.compareTo(BigDecimal.ZERO) < 0)) {
			return false;
		}

		if (hasRequiredAmount() && (requiredAmount.compareTo(BigDecimal.ZERO) < 0)) {
			return false;
		}

		return true;
	}
}