package com.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.cat.config.FileStorageProperties;
import com.cat.config.ImageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class, ImageProperties.class})
public class StationkittiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationkittiesApplication.class, args);
	}
}
