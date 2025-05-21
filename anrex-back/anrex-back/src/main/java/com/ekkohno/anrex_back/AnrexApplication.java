package com.ekkohno.anrex_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AnrexApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnrexApplication.class, args);
	}

}
