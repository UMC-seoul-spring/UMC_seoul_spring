package com.umc.foody.domain.mission.repository;

import static com.umc.foody.domain.mission.entity.QMission.*;
import static com.umc.foody.domain.restaurant.entity.QRestaurant.*;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.mission.enums.MissionSortType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MissionQuerydslRepositoryImpl implements MissionQuerydslRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<Mission> findMissionsInSameEmd(String emd, Point userLocation, MissionSortType sortType,
		Pageable pageable) {

		var query = queryFactory
			.selectFrom(mission)
			.join(mission.restaurant, restaurant)
			.fetchJoin()
			.where(
				restaurant.address.emd.eq(emd),
				restaurant.address.location.isNotNull()
			);

		// 정렬 조건 적용
		JPAQuery<Mission> result;

		switch (sortType) {
			case DISTANCE:
				// MySQL의 ST_Distance_Sphere 함수를 사용하여 거리 계산 후 정렬
				NumberExpression<Double> distance = Expressions.numberTemplate(Double.class,
					"ST_Distance_Sphere({0}, ST_GeomFromText('POINT({1} {2})', 4326))",
					restaurant.address.location,
					userLocation.getX(), // 경도
					userLocation.getY()  // 위도
				);
				result = query.orderBy(distance.asc());
				break;

			case LATEST:
				result = query.orderBy(mission.createdAt.desc());
				break;

			case OLDEST:
				result = query.orderBy(mission.createdAt.asc());
				break;

			default:
				result = query.orderBy(mission.createdAt.desc());
				break;// 기본값: 최신순
		}
		query = result;

		List<Mission> missions = query
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		boolean hasNext = false;
		if (missions.size() > pageable.getPageSize()) {
			missions.remove(pageable.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(missions, pageable, hasNext);
	}

}