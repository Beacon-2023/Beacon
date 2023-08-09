package com.BEACON.beacon.domain.scraping.repository;

import static com.BEACON.beacon.domain.scraping.dto.DisasterMapper.toDto;
import static com.BEACON.beacon.domain.scraping.dto.DisasterMapper.toEntity;

import com.BEACON.beacon.domain.scraping.domain.Disaster;
import com.BEACON.beacon.domain.scraping.dto.DisasterDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapingRepository {

    @PersistenceContext
    EntityManager em;

    public void save(DisasterDto dto) {
        em.persist(toEntity(dto));
    }

    public DisasterDto find(Long id) {
        Disaster disaster = em.find(Disaster.class, id);
        return toDto(disaster);
    }

}
