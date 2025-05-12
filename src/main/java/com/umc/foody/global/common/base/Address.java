package com.umc.foody.global.common.base;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

	private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);

	@Column(name = "sido", length = 20)
	private String sido;

	@Column(name = "sigg", length = 20)
	private String sigg;

	@Column(name = "emd", length = 20)
	private String emd;

	@Column(name = "detail_address", length = 100)
	private String detailAddress;

	@Column(name = "zipcode", length = 5)
	private String zipcode;

	// MySQL Point 타입
	@Column(name = "location", columnDefinition = "POINT")
	private Point location;

	public static Point createPoint(double longitude, double latitude) {
		return GEOMETRY_FACTORY.createPoint(new Coordinate(longitude, latitude));
	}

	public String getFullAddress() {
		return (sido != null ? sido : "") + " " +
			(sigg != null ? sigg : "") + " " +
			(emd != null ? emd : "") + " " +
			(detailAddress != null ? detailAddress : "");
	}

	public boolean hasLocation() {
		return location != null;
	}

	public Double getLatitude() {
		return location != null ? location.getY() : null;
	}

	public Double getLongitude() {
		return location != null ? location.getX() : null;
	}

	public Address updateLocation(double longitude, double latitude) {
		return Address.builder()
			.sido(this.sido)
			.sigg(this.sigg)
			.emd(this.emd)
			.detailAddress(this.detailAddress)
			.zipcode(this.zipcode)
			.location(createPoint(longitude, latitude))
			.build();
	}
}