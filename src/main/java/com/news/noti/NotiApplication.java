package com.news.noti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NotiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotiApplication.class, args);
	}

}
