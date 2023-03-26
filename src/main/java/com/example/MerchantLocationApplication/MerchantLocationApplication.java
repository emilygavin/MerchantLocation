package com.example.MerchantLocationApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MerchantLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantLocationApplication.class, args);
	}

	@Bean
	CommandLineRunner runner () {
		return args -> {};
	}
}
