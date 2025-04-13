package com.umc.foody.global.common.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"timestamp", "code", "message", "result"}) // JSON 응답 시 순서를 정의
public class BaseResponse<T> {

	private final LocalDateTime timestamp = LocalDateTime.now();
	private final String code;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T result;

	//성공한 경우 응답 생성
	public static <T> BaseResponse<T> onSuccess(T result) {
		return new BaseResponse<>("COMMON200", "요청에 성공하였습니다.", result);
	}

	public static <T> BaseResponse<T> onSuccessCreate(T result) {
		return new BaseResponse<>("COMMON201", "요청에 성공하였습니다.", result);
	}

	public static <T> BaseResponse<T> onSuccessDelete(T result) {
		return new BaseResponse<>("COMMON202", "삭제 요청에 성공하였습니다.", result);
	}

	public static <T> BaseResponse<T> of(BaseCodeInterface code, T result) {
		return new BaseResponse<>(code.getCode().getCode(), code.getCode().getMessage(), result);
	}

	// 실패한 경우 응답 생성
	public static <T> BaseResponse<T> onFailure(String code, String message, T data) {
		return new BaseResponse<>(code, message, data);
	}

}

