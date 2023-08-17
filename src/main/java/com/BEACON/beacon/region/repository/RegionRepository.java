package com.BEACON.beacon.region.repository;

import com.BEACON.beacon.region.domain.Region;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {

    Optional<Region> findRegionByCode(String code);
}
