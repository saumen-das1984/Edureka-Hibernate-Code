package com.hibernate.config.xml.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernate.config.xml.model.Student;

public class DataManager {
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("start");
		Student student = new Student();
		student.setId(3);
		student.setFirstName("Nikhil");
		student.setLastName("Lal");
		student.setEmail("nikhil@bananandane.com");
		session.save(student);
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("successfully saved");
	}
}
