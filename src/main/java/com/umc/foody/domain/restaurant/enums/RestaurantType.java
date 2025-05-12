package com.umc.foody.domain.restaurant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantType {

	CHINESE("중식당"),
	JAPANESE("일식당"),
	KOREAN("한식당")
	;

	private final String description;


}
