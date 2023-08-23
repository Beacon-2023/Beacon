package com.BEACON.beacon.location.dao;

import com.BEACON.beacon.location.domain.RegionTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionTokenRepository extends JpaRepository<RegionTokenEntity,String> {

}
