package com.umc.foody.global.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public class MissionApiDocs {

	/**
	 * 미션 정렬 파라미터
	 */
	@Target({ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Parameter(
		description = "정렬 방식을 선택합니다.\n" +
			"- DISTANCE: 사용자 위치로부터의 거리순 (가까운 순)\n" +
			"- LATEST: 최신 생성순\n" +
			"- OLDEST: 오래된 생성순",
		example = "DISTANCE",
		schema = @Schema(allowableValues = {"DISTANCE", "LATEST", "OLDEST"})
	)
	public @interface SortType {
	}

	/**
	 * 내 주변 미션 조회 API
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Operation(
		summary = "내 주변 미션 조회",
		description = "현재 사용자의 위치를 기준으로 같은 지역에 있는 미션들을 조회합니다. " +
			"거리순, 최신순, 오래된순으로 정렬할 수 있으며, 페이지네이션을 지원합니다."
	)
	@CommonApiResponses.GetSuccess
	public @interface GetNearbyMissions {
	}

	/**
	 * 미션 생성 API
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Operation(
		summary = "새 미션 생성",
		description = "새로운 미션을 생성합니다. 미션 제목, 설명, 보상 포인트, " +
			"완료 조건 등을 설정할 수 있습니다. " +
			"생성된 미션은 같은 지역의 다른 사용자들이 참여할 수 있습니다."
	)
	@CommonApiResponses.PostSuccess
	public @interface CreateMission {
	}

	/**
	 * 미션 수정 API
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Operation(
		summary = "미션 정보 수정",
		description = "미션 소유자만 해당 미션의 정보를 수정할 수 있습니다. " +
			"미션 제목, 설명, 보상 포인트 등을 수정할 수 있습니다."
	)
	@CommonApiResponses.PatchSuccess
	public @interface UpdateMission {
	}

	/**
	 * 미션 삭제 API
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Operation(
		summary = "미션 삭제",
		description = "미션 소유자만 해당 미션을 삭제할 수 있습니다. " +
			"삭제된 미션은 복구할 수 없으므로 주의하세요."
	)
	@CommonApiResponses.DeleteSuccess
	public @interface DeleteMission {
	}
}