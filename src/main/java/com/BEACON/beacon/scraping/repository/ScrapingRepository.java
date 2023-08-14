package com.BEACON.beacon.scraping.repository;

import static com.BEACON.beacon.scraping.dto.DisasterAlertMapper.toDto;
import static com.BEACON.beacon.scraping.dto.DisasterAlertMapper.toEntity;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapingRepository extends JpaRepository<DisasterAlert, Long> {

    @Override
    boolean existsById(Long id);

}
