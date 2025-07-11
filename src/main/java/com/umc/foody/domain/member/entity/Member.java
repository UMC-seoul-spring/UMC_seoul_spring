package com.umc.foody.domain.member.entity;

import com.umc.foody.domain.member.enums.Gender;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.umc.foody.global.common.base.Address;
import com.umc.foody.global.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "member_type")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	private String userName;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false)
	private BigDecimal point = BigDecimal.ZERO; //default 값 0으로 설정

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private String birthdate;

	@Column(length = 13)
	private String phoneNumber;

	@Column(nullable = false)
	private Point location;

	@Embedded
	private Address address;

	@Column(nullable = false)
	private LocalDateTime locationUpdatedAt;

	@Column(nullable = false)
	private Long completedMissionCount;

	/**
	 * 연관관계 매핑
	 */

	// @Builder.Default
	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	// private List<MissionHistory> memberMissonList = new ArrayList<>();
	//
	// @Builder.Default
	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true) //연관 관계에서 제거된 자식 엔티티를 자동 삭제
	// private List<RefreshToken> refreshTokenList = new ArrayList<>();
	//
	// @Builder.Default
	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	// private List<Notification> notificationList = new ArrayList<>();
	//
	// @Builder.Default
	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	// private List<FavoriteFood> favoriteFoodList = new ArrayList<>();
	//
	// @Builder.Default
	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	// private List<UserTerms> userTermsList = new ArrayList<>();
	//
	// @Builder.Default
	// @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	// private List<RegionReward> regionRewardList = new ArrayList<>();

	/**
	 * 비즈니스 로직
	 */

}
