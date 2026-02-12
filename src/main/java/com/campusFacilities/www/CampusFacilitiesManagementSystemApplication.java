package com.campusFacilities.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@EnableKafka
@SpringBootApplication
public class CampusFacilitiesManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusFacilitiesManagementSystemApplication.class, args);
	}

}
