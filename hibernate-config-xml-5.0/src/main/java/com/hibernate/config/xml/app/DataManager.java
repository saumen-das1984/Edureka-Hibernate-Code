package com.hibernate.config.xml.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernate.config.xml.model.Student;

public class DataManager {
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
		    // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
		    // so destroy it manually.
		    StandardServiceRegistryBuilder.destroy( registry );
		}
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
