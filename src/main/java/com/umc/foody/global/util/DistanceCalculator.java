package com.umc.foody.global.util;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator {

	private static final double EARTH_RADIUS_KM = 6371.0;

	/**
	 * Haversine 공식을 사용하여 두 지점 간의 거리를 계산합니다.
	 *
	 * @param point1 첫 번째 지점 (사용자 위치)
	 * @param point2 두 번째 지점 (레스토랑 위치)
	 * @return 거리(km)
	 */
	public double calculateDistanceInKm(Point point1, Point point2) {
		if (point1 == null || point2 == null) {
			return 0.0;
		}

		double lat1 = Math.toRadians(point1.getY());
		double lon1 = Math.toRadians(point1.getX());
		double lat2 = Math.toRadians(point2.getY());
		double lon2 = Math.toRadians(point2.getX());

		double dLat = lat2 - lat1;
		double dLon = lon2 - lon1;

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
			Math.cos(lat1) * Math.cos(lat2) *
				Math.sin(dLon / 2) * Math.sin(dLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS_KM * c;
	}
}