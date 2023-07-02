package com.Mateus_Ulrich.eCommerce_FullProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.Mateus_Ulrich.eCommerce_FullProject.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.Mateus_Ulrich.eCommerce_FullProject.repository"})
@EnableTransactionManagement
public class ECommerce_FullProject {

	public static void main(String[] args) {

		System.out.println(new BCryptPasswordEncoder().encode("123"));
		SpringApplication.run(ECommerce_FullProject.class, args);
	}

}
