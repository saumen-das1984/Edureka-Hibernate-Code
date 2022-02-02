package com.hibernate.config.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.hibernate.config.xml.model"})
public class HibernateConfigXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateConfigXmlApplication.class, args);
	}

}
