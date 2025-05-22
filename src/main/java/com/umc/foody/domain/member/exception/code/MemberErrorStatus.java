package com.umc.foody.domain.member.exception.code;

import org.springframework.http.HttpStatus;

import com.umc.foody.global.common.exception.code.BaseCode;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorStatus implements BaseCodeInterface {

	// 회원 조회 관련 오류
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4041", "해당 회원을 찾을 수 없습니다."),

	// 회원 가입/수정 관련 오류
	MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4091", "이미 존재하는 회원입니다."),
	EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4092", "이미 사용 중인 이메일입니다."),
	PHONE_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4093", "이미 사용 중인 전화번호입니다."),
	MEMBER_REGISTRATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MEMBER5001", "회원 가입에 실패했습니다."),
	MEMBER_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MEMBER5002", "회원 정보 수정에 실패했습니다."),

	// 인증 관련 오류
	INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "MEMBER4011", "잘못된 로그인 정보입니다."),
	PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "MEMBER4001", "비밀번호가 일치하지 않습니다."),
	ACCOUNT_LOCKED(HttpStatus.FORBIDDEN, "MEMBER4031", "계정이 잠겼습니다. 관리자에게 문의하세요."),
	ACCOUNT_INACTIVE(HttpStatus.FORBIDDEN, "MEMBER4032", "비활성화된 계정입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER4012", "만료된 토큰입니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER4013", "유효하지 않은 토큰입니다."),

	// 소셜 로그인 관련 오류
	SOCIAL_ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4042", "연결된 소셜 계정을 찾을 수 없습니다."),
	SOCIAL_ACCOUNT_LINK_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MEMBER5003", "소셜 계정 연결에 실패했습니다."),

	// 회원 정보 검증 오류
	INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "MEMBER4002", "유효하지 않은 이메일 형식입니다."),
	INVALID_PHONE_FORMAT(HttpStatus.BAD_REQUEST, "MEMBER4003", "유효하지 않은 전화번호 형식입니다."),
	INVALID_BIRTH_DATE(HttpStatus.BAD_REQUEST, "MEMBER4004", "유효하지 않은 생년월일입니다."),
	INVALID_GENDER(HttpStatus.BAD_REQUEST, "MEMBER4005", "유효하지 않은 성별입니다."),

	// 회원 권한 관련 오류
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "MEMBER4033", "접근 권한이 없습니다."),
	INSUFFICIENT_PRIVILEGES(HttpStatus.FORBIDDEN, "MEMBER4034", "해당 작업을 수행할 권한이 부족합니다."),

	// 위치 관련 오류
	LOCATION_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MEMBER5004", "위치 정보 업데이트에 실패했습니다."),
	INVALID_LOCATION(HttpStatus.BAD_REQUEST, "MEMBER4006", "유효하지 않은 위치 정보입니다."),

	// 포인트 관련 오류
	INSUFFICIENT_POINTS(HttpStatus.BAD_REQUEST, "MEMBER4007", "포인트가 부족합니다."),
	POINT_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "MEMBER5005", "포인트 업데이트에 실패했습니다."),

	// 이용약관 관련 오류
	TERMS_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4043", "이용약관을 찾을 수 없습니다."),
	TERMS_AGREEMENT_REQUIRED(HttpStatus.BAD_REQUEST, "MEMBER4008", "필수 이용약관에 동의가 필요합니다.");

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