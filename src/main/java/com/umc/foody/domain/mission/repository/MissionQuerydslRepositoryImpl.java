package com.umc.foody.domain.mission.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MissionQuerydslRepositoryImpl implements MissionQuerydslRepository {

	private final JPAQueryFactory queryFactory;
	
}
