package com.BEACON.beacon.region.repository;

import com.BEACON.beacon.region.domain.RegionAlert;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionAlertRepository extends JpaRepository<RegionAlert, Long> {

    Optional<RegionAlert> findRegionAlertById(Long id);

}
