package com.umc.foody.domain.restaurant.exception.code;

import org.springframework.http.HttpStatus;

import com.umc.foody.global.common.exception.code.BaseCode;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantErrorStatus implements BaseCodeInterface {

	// 레스토랑 조회 관련 오류
	RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT4041", "해당 레스토랑을 찾을 수 없습니다."),

	// 레스토랑 생성/수정 관련 오류
	RESTAURANT_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5001", "레스토랑 생성에 실패했습니다."),
	RESTAURANT_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5002", "레스토랑 정보 수정에 실패했습니다."),
	RESTAURANT_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5003", "레스토랑 삭제에 실패했습니다."),
	DUPLICATE_RESTAURANT(HttpStatus.CONFLICT, "RESTAURANT4091", "이미 존재하는 레스토랑입니다."),

	// 레스토랑 데이터 유효성 검증 오류
	INVALID_RESTAURANT_DATA(HttpStatus.BAD_REQUEST, "RESTAURANT4001", "유효하지 않은 레스토랑 정보입니다."),
	INVALID_RESTAURANT_TYPE(HttpStatus.BAD_REQUEST, "RESTAURANT4002", "유효하지 않은 레스토랑 유형입니다."),
	INVALID_STAR_POINTS(HttpStatus.BAD_REQUEST, "RESTAURANT4003", "유효하지 않은 별점입니다."),

	// 레스토랑 권한 관련 오류
	RESTAURANT_ACCESS_DENIED(HttpStatus.FORBIDDEN, "RESTAURANT4031", "해당 레스토랑에 접근할 권한이 없습니다."),
	NOT_RESTAURANT_OWNER(HttpStatus.FORBIDDEN, "RESTAURANT4032", "해당 레스토랑의 소유자가 아닙니다."),

	// 레스토랑 이미지 관련 오류
	RESTAURANT_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT4042", "레스토랑 이미지를 찾을 수 없습니다."),
	RESTAURANT_IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5004", "레스토랑 이미지 업로드에 실패했습니다."),
	RESTAURANT_IMAGE_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5005", "레스토랑 이미지 삭제에 실패했습니다."),

	// 레스토랑 메뉴 관련 오류
	MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT4043", "메뉴를 찾을 수 없습니다."),
	MENU_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5006", "메뉴 생성에 실패했습니다."),
	MENU_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5007", "메뉴 수정에 실패했습니다."),
	MENU_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5008", "메뉴 삭제에 실패했습니다."),

	// 영업 시간 관련 오류
	BUSINESS_HOUR_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT4044", "영업 시간 정보를 찾을 수 없습니다."),
	BUSINESS_HOUR_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5009", "영업 시간 생성에 실패했습니다."),
	BUSINESS_HOUR_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5010", "영업 시간 수정에 실패했습니다."),
	BUSINESS_HOUR_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5011", "영업 시간 삭제에 실패했습니다."),
	INVALID_BUSINESS_HOUR(HttpStatus.BAD_REQUEST, "RESTAURANT4004", "유효하지 않은 영업 시간입니다."),

	// 위치 관련 오류
	INVALID_LOCATION(HttpStatus.BAD_REQUEST, "RESTAURANT4005", "유효하지 않은 위치 정보입니다."),
	LOCATION_SERVICE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "RESTAURANT5012", "위치 서비스 처리 중 오류가 발생했습니다.");

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