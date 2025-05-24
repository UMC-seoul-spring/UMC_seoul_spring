package com.umc.foody.domain.member.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Customer extends Member {

	@Column(nullable = false, length = 10)
	private String status;

	@Column(columnDefinition = "DATETIME(6)")
	private LocalDateTime inactiveDate;

	@Column(nullable = false)
	private Long totalPoint;
}