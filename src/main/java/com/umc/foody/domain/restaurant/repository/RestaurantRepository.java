package com.umc.foody.domain.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umc.foody.domain.restaurant.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
