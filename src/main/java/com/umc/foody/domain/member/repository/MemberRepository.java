package com.umc.foody.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umc.foody.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
