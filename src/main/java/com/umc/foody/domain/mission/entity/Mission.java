package com.umc.foody.domain.mission.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class Mission extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_id")
	private Long id;

	/**
	 * 연관 관계 매핑
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@Builder.Default
	@OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MissionHistory> missionHistories = new ArrayList<>();

	/**
	 * 필드
	 */
	private BigDecimal reward; // 보상 금액

	private BigDecimal requiredAmount; // 최소 결제 금액

	/**
	 * 생성 팩토리 메서드
	 */
	public static Mission createMission(BigDecimal reward, BigDecimal requiredAmount, Restaurant restaurant) {
		return Mission.builder()
			.reward(reward)
			.requiredAmount(requiredAmount)
			.restaurant(restaurant)
			.build();
	}

}
