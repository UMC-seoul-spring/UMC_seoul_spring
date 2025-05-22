package com.umc.foody.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OWNER")
public class Owner extends Member {

    @Column(nullable = false, length = 20)
    private String store;
}
