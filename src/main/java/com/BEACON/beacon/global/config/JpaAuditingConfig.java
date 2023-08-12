package com.BEACON.beacon.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 현 생성시간을 받아오기 위해 만든 클래스
 * 메인클래스에 해당 어노테이션을 올려놓으면 테스트를 진행하는데 불편함이 있으므로 Config 클래스로 생성
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
