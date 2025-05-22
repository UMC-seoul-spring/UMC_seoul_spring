package com.umc.foody.domain.mission.service.query;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umc.foody.domain.member.entity.Member;
import com.umc.foody.domain.member.exception.MemberException;
import com.umc.foody.domain.member.exception.code.MemberErrorStatus;
import com.umc.foody.domain.member.repository.MemberRepository;
import com.umc.foody.domain.mission.dto.response.GetMissionResponseDto;
import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.mission.repository.MissionQuerydslRepository;
import com.umc.foody.global.util.DistanceCalculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

	private final MissionQuerydslRepository missionQuerydslRepository;
	private final MemberRepository memberRepository;
	private final DistanceCalculator distanceCalculator;

	@Override
	public Slice<GetMissionResponseDto> getMissionsNearBy(User user, Pageable pageable) {
		// 현재 사용자 조회
		Member currentMember = memberRepository.findByUsername(user.getUsername())
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));

		// 사용자의 위치 정보 확인
		if (currentMember.getAddress() == null || !currentMember.getAddress().hasLocation()) {
			throw new MemberException(MemberErrorStatus.INVALID_LOCATION);
		}

		Point userLocation = currentMember.getAddress().getLocation();
		String userEmd = currentMember.getAddress().getEmd();

		log.info("사용자 위치: {} (위도: {}, 경도: {})", userEmd,
			userLocation.getY(), userLocation.getX());

		// 같은 지역(emd) 내 미션들 조회
		Slice<Mission> missions = missionQuerydslRepository.findMissionsInSameEmd(
			userEmd, userLocation, pageable);

		// 미션들을 DTO로 변환하면서 거리 계산
		return missions.map(mission -> {
			Point restaurantLocation = mission.getRestaurant().getAddress().getLocation();
			double distanceKm = distanceCalculator.calculateDistanceInKm(userLocation, restaurantLocation);

			// 소수점 둘째 자리까지 반올림
			distanceKm = Math.round(distanceKm * 100.0) / 100.0;

			return GetMissionResponseDto.of(mission, mission.getRestaurant(), distanceKm);
		});
	}
}