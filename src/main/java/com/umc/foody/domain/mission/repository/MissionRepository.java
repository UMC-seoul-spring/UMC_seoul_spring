package com.umc.foody.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umc.foody.domain.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}
