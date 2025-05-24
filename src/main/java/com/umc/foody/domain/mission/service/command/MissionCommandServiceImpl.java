package com.umc.foody.domain.mission.service.command;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.umc.foody.domain.member.entity.Member;
import com.umc.foody.domain.member.entity.Owner;
import com.umc.foody.domain.member.exception.MemberException;
import com.umc.foody.domain.member.exception.code.MemberErrorStatus;
import com.umc.foody.domain.member.repository.MemberRepository;
import com.umc.foody.domain.mission.dto.request.CreateMissionRequestDto;
import com.umc.foody.domain.mission.dto.request.PatchMissionRequestDto;
import com.umc.foody.domain.mission.dto.response.CreateMissionResponseDto;
import com.umc.foody.domain.mission.dto.response.PatchMissionResponseDto;
import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.mission.exception.MissionException;
import com.umc.foody.domain.mission.exception.code.MissionErrorStatus;
import com.umc.foody.domain.mission.repository.MissionRepository;
import com.umc.foody.domain.mission.service.domain.MissionDomainService;
import com.umc.foody.domain.restaurant.entity.Restaurant;
import com.umc.foody.domain.restaurant.exception.RestaurantException;
import com.umc.foody.domain.restaurant.exception.code.RestaurantErrorStatus;
import com.umc.foody.domain.restaurant.repository.RestaurantRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

	private final MissionRepository missionRepository;
	private final MemberRepository memberRepository;
	private final RestaurantRepository restaurantRepository;
	private final MissionDomainService missionDomainService;

	@Override
	public CreateMissionResponseDto createMission(User currentUser, CreateMissionRequestDto request) {

		Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
			.orElseThrow(() -> new RestaurantException(RestaurantErrorStatus.RESTAURANT_NOT_FOUND));

		Member currentMember = memberRepository.findByUsername(currentUser.getUsername())
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));

		if (currentMember instanceof Owner owner) {
			// 기존 미션들 조회 (복잡한 비즈니스 규칙이 있는 경우)
			List<Mission> existingMissions = missionRepository.findByRestaurant(restaurant);

			// Domain Service 활용 (복잡한 규칙이 있는 경우)
			Mission newMission = missionDomainService.createMissionWithBusinessRules(
				null, // title은 자동 생성
				request.getReward(),
				request.getRequiredAmount(),
				restaurant,
				existingMissions
			);

			// 또는 단순한 경우 엔티티 팩토리 메서드 직접 사용
			// Mission newMission = Mission.createMission(null, request.getReward(), request.getRequiredAmount(), restaurant);

			missionRepository.save(newMission);
			return CreateMissionResponseDto.from(newMission, restaurant);

		} else {
			throw new MemberException(MemberErrorStatus.ONLY_OWNER_ALLOWED);
		}
	}

	@Override
	public PatchMissionResponseDto updateMission(User currentUser, PatchMissionRequestDto request,
		Long missionId) {

		// 미션 조회
		Mission mission = missionRepository.findById(missionId)
			.orElseThrow(() -> new MissionException(MissionErrorStatus.MISSION_NOT_FOUND));

		// 현재 사용자 조회
		Member currentMember = memberRepository.findByEmail(currentUser.getUsername())
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));

		// Owner 확인
		if (currentMember instanceof Owner owner && mission.getRestaurant()
			.getId()
			.equals(owner.getRestaurant().getId())) {

			if (request.hasReward()) {
				mission.updateReward(request.getReward());
			}

			if (request.hasRequiredAmount()) {
				mission.updateRequiredAmount(request.getRequiredAmount());
			}

			Mission updatedMission = missionRepository.save(mission);

			return PatchMissionResponseDto.from(updatedMission);
		} else {
			throw new MemberException(MemberErrorStatus.ONLY_OWNER_ALLOWED);
		}
	}

	@Override
	public Void deleteMission(User currentUser, Long missionId) {
		Mission mission = missionRepository.findById(missionId)
			.orElseThrow(() -> new MissionException(MissionErrorStatus.MISSION_NOT_FOUND));

		Member member = memberRepository.findByEmail(currentUser.getUsername())
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));

		// 가게 주인이고 미션의 주인일 경우만 삭제 하도록 함.
		if (member instanceof Owner owner && mission.getRestaurant().getId().equals(owner.getRestaurant().getId())) {
			missionRepository.delete(mission);
		}

		return null;
	}
}
