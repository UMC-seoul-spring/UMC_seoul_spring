package com.umc.foody.domain.mission.repository;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.umc.foody.domain.mission.entity.Mission;

public interface MissionQuerydslRepository {

	Slice<Mission> findMissionsInSameEmd(String emd, Point userLocation, Pageable pageable);

}
