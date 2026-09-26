package com.jobshield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//mvn spring-boot:run "-Dspring-boot.run.jvmArguments=-Duser.timezone=Asia/Kolkata"
@SpringBootApplication
@EnableJpaAuditing
public class JobshieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobshieldApplication.class, args);
	}

}