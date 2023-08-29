package com.BEACON.beacon.fcm.dao;

import com.BEACON.beacon.fcm.domain.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity,Long> {
}
