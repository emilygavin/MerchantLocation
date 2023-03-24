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
	CommandLineRunner runner (MerchantRepository repository) {
		return args -> {
			Merchant merchant = new Merchant(
					"52.986375", "-6.043701", 12, "Tesco"
			);

			repository.insert(merchant);
		};
	}

}
