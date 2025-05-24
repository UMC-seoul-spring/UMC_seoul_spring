// src/main/java/com/umc/foody/global/swagger/CommonApiResponses.java
package com.umc.foody.global.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class CommonApiResponses {

	/**
	 * 조회 성공 응답
	 */
	@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "조회 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "401",
			description = "인증되지 않은 요청 - 로그인이 필요합니다",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "잘못된 요청 파라미터",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		)
	})
	public @interface GetSuccess {
	}

	/**
	 * 생성 성공 응답
	 */
	@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "생성 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "401",
			description = "인증되지 않은 요청",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "유효하지 않은 요청 데이터",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		)
	})
	public @interface PostSuccess {
	}

	/**
	 * 수정 성공 응답
	 */
	@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "수정 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "401",
			description = "인증되지 않은 요청",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "403",
			description = "권한 없음 - 해당 리소스를 수정할 권한이 없습니다",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "존재하지 않는 리소스",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "400",
			description = "유효하지 않은 요청 데이터",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		)
	})
	public @interface PatchSuccess {
	}

	/**
	 * 삭제 성공 응답
	 */
	@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "삭제 성공"
		),
		@ApiResponse(
			responseCode = "401",
			description = "인증되지 않은 요청",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "403",
			description = "권한 없음 - 해당 리소스를 삭제할 권한이 없습니다",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		),
		@ApiResponse(
			responseCode = "404",
			description = "존재하지 않는 리소스",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = com.umc.foody.global.common.base.BaseResponse.class)
			)
		)
	})
	public @interface DeleteSuccess {
	}
}