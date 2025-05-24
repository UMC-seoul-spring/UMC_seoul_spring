package com.umc.foody.domain.mission.exception;

import com.umc.foody.global.common.exception.RestApiException;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

public class MissionException extends RestApiException {
	public MissionException(BaseCodeInterface errorCode) {
		super(errorCode);
	}
}
