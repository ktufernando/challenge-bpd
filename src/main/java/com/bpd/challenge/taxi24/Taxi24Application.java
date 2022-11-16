package com.bpd.challenge.taxi24;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Taxi24Application {

	public static void main(String[] args) {
		SpringApplication.run(Taxi24Application.class, args);
	}

	@Bean
	CommandLineRunner initData(final DataInitializer initializer) {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				log.info(
						"\n*********** Initializing Data ***********");
				initializer.initData();
			}
		};
	}

}
