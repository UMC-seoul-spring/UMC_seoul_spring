package com.umc.foody.global.common.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder  // 상속 체인의 최상위에 SuperBuilder 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA에서 필요
@AllArgsConstructor(access = AccessLevel.PROTECTED)  // SuperBuilder에서 필요
public class BaseEntity {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;  // 이 필드는 Spring이 자동으로 관리

	@LastModifiedDate
	private LocalDateTime updatedAt;  // 이 필드도 Spring이 자동으로 관리

}