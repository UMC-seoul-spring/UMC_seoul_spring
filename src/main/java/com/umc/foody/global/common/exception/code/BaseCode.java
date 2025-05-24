package com.umc.foody.global.common.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BaseCode {
	private final HttpStatus httpStatus;
	private final boolean isSuccess;
	private final String code;
	private final String message;
}