package com.jobshield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// Exclude DataSource until it's configured to prevent startup failure
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class JobshieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobshieldApplication.class, args);
	}

}
