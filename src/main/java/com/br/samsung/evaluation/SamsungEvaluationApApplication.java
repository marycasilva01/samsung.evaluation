package com.br.samsung.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
public class SamsungEvaluationApApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamsungEvaluationApApplication.class, args);
	}

}
