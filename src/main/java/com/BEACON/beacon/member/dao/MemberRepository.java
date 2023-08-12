package com.BEACON.beacon.member.dao;

import com.BEACON.beacon.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);



}
