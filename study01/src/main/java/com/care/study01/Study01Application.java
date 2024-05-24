package com.care.study01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener을 활성화 시키기 위해 설정
public class Study01Application {

	public static void main(String[] args) {
		SpringApplication.run(Study01Application.class, args);
	}

}
