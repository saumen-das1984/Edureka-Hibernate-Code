package com.hibernate.association.xml.app;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.association.xml.model.Company;
import com.hibernate.association.xml.model.Job;
import com.hibernate.association.xml.util.HibernateUtil;


public class DataManager {
	public static void main(String[] args) {
		saveJob();
	}
	
	private static void saveJob() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Set<Company> companies = new HashSet<>();
			Company company1 = new Company();
			company1.setCompanyName("Google");
			companies.add(company1);
			
			Company company2 = new Company();
			company2.setCompanyName("TCS");
			companies.add(company2);
			
			Company company3 = new Company();
			company3.setCompanyName("Amazon");
			companies.add(company3);
			
			Job job = new Job();
			job.setJobName("CEO");
			job.setCompany(companies);
			
			session.save(job);
			
			transaction.commit();
			System.out.println("Dateils Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
