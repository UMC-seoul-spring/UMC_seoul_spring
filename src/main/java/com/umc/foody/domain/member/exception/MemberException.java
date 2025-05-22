package com.umc.foody.domain.member.exception;

import com.umc.foody.global.common.exception.RestApiException;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

public class MemberException extends RestApiException {
	public MemberException(BaseCodeInterface errorCode) {
		super(errorCode);
	}
}
