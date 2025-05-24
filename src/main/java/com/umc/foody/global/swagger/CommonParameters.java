package com.umc.foody.global.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public class CommonParameters {

	/**
	 * 페이지 번호 파라미터
	 */
	@Target({ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Parameter(
		description = "페이지 번호 (0부터 시작)",
		example = "0",
		schema = @Schema(minimum = "0", defaultValue = "0")
	)
	public @interface PageNumber {
	}

	/**
	 * 페이지 크기 파라미터
	 */
	@Target({ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Parameter(
		description = "페이지 크기 (최대 50개까지 가능)",
		example = "10",
		schema = @Schema(minimum = "1", maximum = "50", defaultValue = "10")
	)
	public @interface PageSize {
	}

	/**
	 * ID 파라미터
	 */
	@Target({ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Parameter(
		description = "리소스의 고유 ID",
		example = "1",
		required = true,
		schema = @Schema(minimum = "1")
	)
	public @interface ResourceId {
	}
}