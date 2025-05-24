package com.umc.foody.domain.mission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umc.foody.domain.mission.entity.Mission;
import com.umc.foody.domain.restaurant.entity.Restaurant;

public interface MissionRepository extends JpaRepository<Mission, Long> {

	List<Mission> findByRestaurant(Restaurant restaurant);

}
