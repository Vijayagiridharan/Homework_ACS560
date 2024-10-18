package com.acs560.rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The main class for the REST API application.
 * <p>
 * This is the entry point of the Spring Boot application. The {@code @SpringBootApplication} 
 * annotation enables auto-configuration and component scanning for the application.
 * </p>
 */
@SpringBootApplication
public class RestApiApplication {
    
    /**
     * The main method that launches the Spring Boot application.
     *
     * @param args Command-line arguments passed during the application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
    
}

