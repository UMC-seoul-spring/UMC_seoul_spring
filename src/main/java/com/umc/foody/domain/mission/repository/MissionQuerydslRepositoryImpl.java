package com.umc.foody.domain.mission.repository;

import static com.umc.foody.domain.mission.entity.QMission.*;
import static com.umc.foody.domain.restaurant.entity.QRestaurant.*;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.foody.domain.mission.entity.Mission;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MissionQuerydslRepositoryImpl implements MissionQuerydslRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<Mission> findMissionsInSameEmd(String emd, Point userLocation, Pageable pageable) {
		List<Mission> missions = queryFactory
			.selectFrom(mission)
			.join(mission.restaurant, restaurant)
			.fetchJoin()
			.where(
				restaurant.address.emd.eq(emd),
				restaurant.address.location.isNotNull()
			)
			.orderBy(mission.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1) // +1로 다음 페이지 존재 여부 확인
			.fetch();

		boolean hasNext = false;
		if (missions.size() > pageable.getPageSize()) {
			missions.remove(pageable.getPageSize());
			hasNext = true;
		}

		return new SliceImpl<>(missions, pageable, hasNext);
	}

}
