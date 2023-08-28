package com.BEACON.beacon.location.dao;

import com.BEACON.beacon.location.domain.RegionTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionTokenRepository extends JpaRepository<RegionTokenEntity,String> {
    List<RegionTokenEntity> findByLegalDongCodeIn(List<String> legalDongCode);

}
