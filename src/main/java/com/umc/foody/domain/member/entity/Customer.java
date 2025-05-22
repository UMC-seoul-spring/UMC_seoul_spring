package com.umc.foody.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Member {

    @Column(nullable = false, length = 10)
    private String status;

    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime inactiveDate;

    @Column(nullable = false)
    private Long totalPoint;
}