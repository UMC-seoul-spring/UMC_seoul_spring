package com.umc.foody.domain.mission.service.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.mission.exception.MissionException;
import com.umc.foody.domain.mission.exception.code.MissionErrorStatus;
import com.umc.foody.domain.restaurant.entity.Restaurant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MissionDomainService {

	/**
	 * 복잡한 미션 생성 로직 (여러 엔티티 간의 협력이 필요한 경우)
	 * 예: 레스토랑의 기존 미션들과 중복 체크, 레스토랑 정책에 따른 제약 확인 등
	 */
	public Mission createMissionWithBusinessRules(
		String title,
		BigDecimal reward,
		BigDecimal requiredAmount,
		Restaurant restaurant,
		List<Mission> existingMissions) {

		// 중복 미션 체크 (여러 엔티티 간의 로직)
		validateNoDuplicateMission(requiredAmount, existingMissions);

		// 레스토랑별 미션 개수 제한 확인
		validateMissionLimit(restaurant, existingMissions);

		// Mission 엔티티의 팩토리 메서드 호출
		Mission mission = Mission.createMission(title, reward, requiredAmount, restaurant);

		log.info("Created mission for restaurant {} with business rules applied",
			restaurant.getName());

		return mission;
	}

	/**
	 * 미션 종료 처리 (여러 엔티티에 걸친 복잡한 로직)
	 */
	public void completeMission(Mission mission, BigDecimal paidAmount) {
		if (!mission.canBeCompletedWith(paidAmount)) {
			throw new MissionException(MissionErrorStatus.INSUFFICIENT_PAYMENT_AMOUNT);
		}

		// 복잡한 보상 지급 로직, 통계 업데이트 등은 여기서 처리
		log.info("Mission {} completed with payment {}", mission.getId(), paidAmount);
	}

	// Private helper methods
	private void validateNoDuplicateMission(BigDecimal requiredAmount, List<Mission> existingMissions) {
		boolean hasDuplicate = existingMissions.stream()
			.anyMatch(m -> m.getRequiredAmount().compareTo(requiredAmount) == 0);

		if (hasDuplicate) {
			throw new MissionException(MissionErrorStatus.DUPLICATE_MISSION);
		}
	}

	private void validateMissionLimit(Restaurant restaurant, List<Mission> existingMissions) {
		if (existingMissions.size() >= 10) { // 예시: 레스토랑당 최대 10개 미션
			throw new MissionException(MissionErrorStatus.MISSION_CREATE_FAILED);
		}
	}
}