package com.example.productosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProductosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosApiApplication.class, args);
	}

}
