package com.umc.foody.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

    @Column(name = "zip_code", nullable = false)
    private String zipCode;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "addressDetail", nullable = false)
    private String addressDetail;
}
