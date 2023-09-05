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
            "(:x - LATITUDE) * (:x - LATITUDE) + (:y - LONGITUDE) * (:y - LONGITUDE) AS distance " +
            "FROM SHELTER " +
            "WHERE SHELTER_CATEGORY = :category " +
            "ORDER BY distance " +
            "LIMIT :count", nativeQuery = true)
    List<Shelter> findNearestShelters(
            @Param("x") Double x,
            @Param("y") Double y,
            @Param("category") String category,
            @Param("count") Integer count);
}

