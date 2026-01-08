package com.dogukantez.autogallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.dogukantez"})
@EntityScan(basePackages = {"com.dogukantez"})
@EnableJpaRepositories(basePackages = {"com.dogukantez"})
@SpringBootApplication
public class AutoGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoGalleryApplication.class, args);
	}

}
