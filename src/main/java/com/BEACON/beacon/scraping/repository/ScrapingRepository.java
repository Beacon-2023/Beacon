package com.BEACON.beacon.scraping.repository;

import static com.BEACON.beacon.scraping.dto.DisasterAlertMapper.toDto;
import static com.BEACON.beacon.scraping.dto.DisasterAlertMapper.toEntity;

import com.BEACON.beacon.scraping.domain.DisasterAlert;
import com.BEACON.beacon.scraping.dto.DisasterAlertDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapingRepository {

    @PersistenceContext
    EntityManager em;

    public void save(DisasterAlert disasterAlert) {
        em.persist(disasterAlert);
    }

    public DisasterAlert findById(Long id) {
        return em.find(DisasterAlert.class, id);
    }

}
