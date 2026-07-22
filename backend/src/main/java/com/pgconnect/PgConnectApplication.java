package com.pgconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for PG Connect Backend.
 * <p>
 * This acts as the entry point for the Spring Boot Application.
 * Component scanning will start from the com.pgconnect package.
 */
@SpringBootApplication
public class PgConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgConnectApplication.class, args);
	}

}
