package com.umc.foody.domain.mission.exception.code;

import org.springframework.http.HttpStatus;

import com.umc.foody.global.common.exception.code.BaseCode;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionErrorStatus implements BaseCodeInterface {

	// 미션 조회 관련 오류
	MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4041", "해당 미션을 찾을 수 없습니다."),

	// 미션 생성/수정 관련 오류
	INVALID_MISSION_REQUEST(HttpStatus.BAD_REQUEST, "MISSION4001", "유효하지 않은 미션 요청입니다."),
	DUPLICATE_MISSION(HttpStatus.CONFLICT, "MISSION4091", "이미 존재하는 미션입니다."),
	MISSION_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MISSION5001", "미션 생성에 실패했습니다."),
	MISSION_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MISSION5002", "미션 수정에 실패했습니다."),
	MISSION_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MISSION5003", "미션 삭제에 실패했습니다."),

	// 미션 수행 관련 오류
	MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "MISSION4092", "이미 완료된 미션입니다."),
	MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION4002", "만료된 미션입니다."),
	INSUFFICIENT_PAYMENT_AMOUNT(HttpStatus.BAD_REQUEST, "MISSION4003", "결제 금액이 미션 요구사항을 충족하지 않습니다."),

	// 미션 보상 관련 오류
	REWARD_PROCESSING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MISSION5004", "미션 보상 처리에 실패했습니다."),

	// 미션 권한 관련 오류
	MISSION_ACCESS_DENIED(HttpStatus.FORBIDDEN, "MISSION4031", "해당 미션에 접근할 권한이 없습니다."),

	// 미션 레스토랑 관련 오류
	RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4042", "미션과 연결된 레스토랑을 찾을 수 없습니다."),

	// 미션 히스토리 관련 오류
	MISSION_HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4043", "미션 히스토리를 찾을 수 없습니다."),
	MISSION_HISTORY_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MISSION5005", "미션 히스토리 생성에 실패했습니다.");

	private final HttpStatus httpStatus;
	private final boolean isSuccess = false;
	private final String code;
	private final String message;

	@Override
	public BaseCode getCode() {
		return BaseCode.builder()
			.httpStatus(httpStatus)
			.isSuccess(isSuccess)
			.code(code)
			.message(message)
			.build();
	}
}