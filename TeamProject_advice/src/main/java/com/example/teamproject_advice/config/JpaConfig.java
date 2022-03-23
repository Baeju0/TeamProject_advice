package com.example.teamproject_advice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration          // 설정 파일을 선언
@EnableJpaAuditing      // JPA의 감시를 활성화
public class JpaConfig {
}
