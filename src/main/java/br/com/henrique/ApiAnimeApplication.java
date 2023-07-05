package br.com.henrique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class ApiAnimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAnimeApplication.class, args);
	}

}
