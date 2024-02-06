package com.bvktest.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(InventoryApplication.class, args);

		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

		System.out.println("aaa");
	}

}
