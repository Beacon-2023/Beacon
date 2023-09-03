package com.BEACON.beacon.guideline.dao;

import com.BEACON.beacon.guideline.domain.BasicGuideLineEntity;
import com.BEACON.beacon.scraping.domain.DisasterCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasicGuideLineRepository extends JpaRepository<BasicGuideLineEntity,Long> {
   Optional<BasicGuideLineEntity> findOptionalByDisaster(DisasterCategory disasterCategory);

}
