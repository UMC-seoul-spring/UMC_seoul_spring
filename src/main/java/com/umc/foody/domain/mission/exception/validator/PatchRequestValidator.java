package com.umc.foody.domain.mission.exception.validator;

import com.umc.foody.domain.mission.dto.request.PatchMissionRequestDto;
import com.umc.foody.domain.mission.exception.annotation.ValidPatchRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PatchRequestValidator implements ConstraintValidator<ValidPatchRequest, PatchMissionRequestDto> {

	@Override
	public boolean isValid(PatchMissionRequestDto dto, ConstraintValidatorContext context) {
		if (dto == null) {
			return false;
		}

		// 업데이트할 필드가 하나도 없는 경우
		if (!dto.hasAnyUpdateField()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("업데이트할 필드가 없습니다")
				.addConstraintViolation();
			return false;
		}

		return dto.isValidUpdateRequest();
	}
}