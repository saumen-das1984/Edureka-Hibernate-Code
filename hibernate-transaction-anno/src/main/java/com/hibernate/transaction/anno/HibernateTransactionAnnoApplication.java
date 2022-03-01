package com.hibernate.transaction.anno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class HibernateTransactionAnnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateTransactionAnnoApplication.class, args);
	}

}
