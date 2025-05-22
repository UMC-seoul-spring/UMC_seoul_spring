package com.umc.foody.domain.member.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.geo.Point;

import com.umc.foody.global.common.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "member_type")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false, length = 100)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private String birthdate;

	@Column(length = 13)
	private String phoneNumber;

	@Column(nullable = false, columnDefinition = "POINT")
	private Point location;

	@Column(nullable = false)
	private LocalDateTime locationUpdatedAt;

	@Column(nullable = false)
	private Long completedMissionCount;

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

}
