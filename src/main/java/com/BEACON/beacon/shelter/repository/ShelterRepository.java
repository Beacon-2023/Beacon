package com.BEACON.beacon.shelter.repository;

import com.BEACON.beacon.shelter.domain.Shelter;
import com.BEACON.beacon.shelter.domain.ShelterCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    Optional<Shelter> findShelterById(Long id);

    @Query(value = "SELECT *, " +
            "(:x - x) * (:x - x) + (:y - y) * (:y - y) AS distance " +
            "FROM shelter " +
            "WHERE category = :category " +
            "ORDER BY distance " +
            "LIMIT :count", nativeQuery = true)
    List<Shelter> findNearestShelters(
            @Param("x") Double x,
            @Param("y") Double y,
            @Param("category") ShelterCategory category,
            @Param("count") Integer count);
}

