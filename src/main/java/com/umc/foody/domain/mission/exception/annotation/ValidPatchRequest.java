package com.umc.foody.domain.mission.exception.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.umc.foody.domain.mission.exception.validator.PatchRequestValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PatchRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPatchRequest {
	String message() default "업데이트할 필드가 없거나 유효하지 않습니다";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
