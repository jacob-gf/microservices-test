package com.jacob.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class MsCustomerPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCustomerPersonApplication.class, args);
	}

}
