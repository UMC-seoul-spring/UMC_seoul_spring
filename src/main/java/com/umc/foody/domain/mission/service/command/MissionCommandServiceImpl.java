package com.umc.foody.domain.mission.service.command;

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

	@Override
	public CreateMissionResponseDto createMission(User currentUser, CreateMissionRequestDto request) {

		// 레스토랑 가져오기
		Restaurant myRestaurant = restaurantRepository.findById(request.getRestaurantId())
			.orElseThrow(() -> new RestaurantException(RestaurantErrorStatus.RESTAURANT_NOT_FOUND));

		// 만약 currentUser가 사장님(OWNER)가 아니면 mission을 생성하지 못하도록 해야함.
		Member currentMember = memberRepository.findByEmail(currentUser.getUsername())
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));

		if (currentMember instanceof Owner owner) {
			log.info("Owner '{}' is creating mission for restaurant '{}'",
				owner.getStore(), myRestaurant.getName());

			Mission newMission = Mission.createMission(
				request.getReward(),
				request.getRequiredAmount(),
				myRestaurant
			);

			missionRepository.save(newMission);

			return CreateMissionResponseDto.from(newMission, myRestaurant);
		} else {
			// Owner가 아닌 경우 예외 발생
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
		Member currentMember = memberRepository.findByUsername(currentUser.getUsername())
			.orElseThrow(() -> new MemberException(MemberErrorStatus.MEMBER_NOT_FOUND));

		// Owner 확인
		if (currentMember instanceof Owner owner) {
			log.info("Owner '{}' is updating mission {}", owner.getStore(), missionId);

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
}
