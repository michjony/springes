package com.mark.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringesApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringesApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringesApplication.class, args);
		LOGGER.info("welcome");
	}
}
