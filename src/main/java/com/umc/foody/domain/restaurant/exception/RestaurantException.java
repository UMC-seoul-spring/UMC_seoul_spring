package com.umc.foody.domain.restaurant.exception;

import com.umc.foody.global.common.exception.RestApiException;
import com.umc.foody.global.common.exception.code.BaseCodeInterface;

public class RestaurantException extends RestApiException {
	public RestaurantException(BaseCodeInterface errorCode) {
		super(errorCode);
	}
}
