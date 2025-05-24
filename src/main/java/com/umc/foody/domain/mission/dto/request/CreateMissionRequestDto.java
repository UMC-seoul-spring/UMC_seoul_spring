package com.umc.foody.domain.mission.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "미션 생성 요청 DTO")
public class CreateMissionRequestDto {

	@NotNull(message = "보상 포인트는 필수입니다")
	@DecimalMin(value = "0", inclusive = true, message = "보상 포인트는 0 이상이어야 합니다")
	@Digits(integer = 10, fraction = 0, message = "보상 포인트는 정수 10자리 이하여야 합니다")
	@Schema(description = "보상 포인트", example = "500", required = true)
	private BigDecimal reward; // 보상 포인트

	@NotNull(message = "최소 결제 금액은 필수입니다")
	@DecimalMin(value = "0", inclusive = true, message = "최소 결제 금액은 0 이상이어야 합니다")
	@Digits(integer = 10, fraction = 2, message = "최소 결제 금액은 소수점 2자리까지 허용합니다")
	@Schema(description = "최소 결제 금액", example = "10000.00", required = true)
	private BigDecimal requiredAmount; // 최소 결제 금액

	@NotNull(message = "레스토랑 ID는 필수입니다")
	@Positive(message = "레스토랑 ID는 양수여야 합니다")
	@Schema(description = "미션이 적용될 레스토랑 ID", example = "1", required = true)
	private Long restaurantId; // 미션이 적용될 레스토랑 ID

}