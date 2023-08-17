package com.BEACON.beacon.scraping.repository;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapingRepository extends JpaRepository<DisasterAlert, Long> {

    @Override
    boolean existsById(Long id);

}
