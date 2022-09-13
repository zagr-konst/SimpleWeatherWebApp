package com.zagr.konst.weatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude={SecurityAutoConfiguration.class}
@SpringBootApplication
public class WeatherWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherWebAppApplication.class, args);
	}

}
