package com.hibernate.association.xml.app;

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
			
			//Set<Company> companies = new HashSet<>();
			Company company1 = new Company();
			company1.setCompanyName("Google");
			//companies.add(company1);
			
			Company company2 = new Company();
			company2.setCompanyName("TCS");
			//companies.add(company2);
			
			Job job1 = new Job();
			job1.setJobName("CEO");
			job1.setCompany(company1);
			
			Job job2 = new Job();
			job2.setJobName("CIO");
			job2.setCompany(company2);
			
			session.save(company1);
			session.save(company2);
			session.save(job1);
			session.save(job2);
			
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
