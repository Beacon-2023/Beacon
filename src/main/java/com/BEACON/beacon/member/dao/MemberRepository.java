package com.BEACON.beacon.member.dao;

import com.BEACON.beacon.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<MemberEntity> findMemberByUserName(String userName);

}
