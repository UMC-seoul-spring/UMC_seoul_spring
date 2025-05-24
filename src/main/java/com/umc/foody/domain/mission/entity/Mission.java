package com.umc.foody.domain.mission.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.umc.foody.domain.mission.exception.MissionException;
import com.umc.foody.domain.mission.exception.code.MissionErrorStatus;
import com.umc.foody.domain.missionhistory.entity.MissionHistory;
import com.umc.foody.domain.restaurant.entity.Restaurant;
import com.umc.foody.global.common.base.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Mission extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@Builder.Default
	@OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MissionHistory> missionHistories = new ArrayList<>();

	private BigDecimal reward;

	private BigDecimal requiredAmount;

	/**
	 * 생성 팩토리 메서드 (엔티티 자체의 책임)
	 */
	public static Mission createMission(String title, BigDecimal reward, BigDecimal requiredAmount,
		Restaurant restaurant) {
		validateCreationInputs(reward, requiredAmount, restaurant);

		return Mission.builder()
			.reward(reward)
			.requiredAmount(requiredAmount)
			.restaurant(restaurant)
			.build();
	}

	private static void validateCreationInputs(BigDecimal reward, BigDecimal requiredAmount, Restaurant restaurant) {
		if (restaurant == null) {
			throw new MissionException(MissionErrorStatus.RESTAURANT_NOT_FOUND);
		}
		validateReward(reward);
		validateRequiredAmount(requiredAmount);
		validateRewardRatio(reward, requiredAmount);
	}

	private static void validateTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new MissionException(MissionErrorStatus.INVALID_MISSION_REQUEST);
		}
		if (title.length() > 100) {
			throw new MissionException(MissionErrorStatus.INVALID_MISSION_REQUEST);
		}
	}

	private static void validateReward(BigDecimal reward) {
		if (reward == null || reward.compareTo(BigDecimal.ZERO) <= 0) {
			throw new MissionException(MissionErrorStatus.INVALID_MISSION_REQUEST);
		}
	}

	private static void validateRequiredAmount(BigDecimal requiredAmount) {
		if (requiredAmount == null || requiredAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new MissionException(MissionErrorStatus.INVALID_MISSION_REQUEST);
		}
	}

	private static void validateRewardRatio(BigDecimal reward, BigDecimal requiredAmount) {
		// 비즈니스 규칙: 보상이 결제 금액의 10%를 초과할 수 없음
		BigDecimal maxReward = requiredAmount.multiply(new BigDecimal("0.1"));
		if (reward.compareTo(maxReward) > 0) {
			throw new MissionException(MissionErrorStatus.INVALID_MISSION_REQUEST);
		}
	}

	// === Private 메서드들 (내부 불변식 보장) ===

	/**
	 * 미션 정보 업데이트 (엔티티 자체의 책임)
	 */
	public void updateReward(BigDecimal newReward) {
		validateReward(newReward);
		validateRewardRatio(newReward, this.requiredAmount);
		this.reward = newReward;
	}

	public void updateRequiredAmount(BigDecimal newRequiredAmount) {
		validateRequiredAmount(newRequiredAmount);
		validateRewardRatio(this.reward, newRequiredAmount);
		this.requiredAmount = newRequiredAmount;
	}

	/**
	 * 미션 완료 가능 여부 확인 (도메인 로직)
	 */
	public boolean canBeCompletedWith(BigDecimal paidAmount) {
		return paidAmount.compareTo(this.requiredAmount) >= 0;
	}

	/**
	 * 미션 활성화 여부 확인 (도메인 로직)
	 */
	public boolean isActive() {
		return restaurant != null && reward.compareTo(BigDecimal.ZERO) > 0;
	}
}