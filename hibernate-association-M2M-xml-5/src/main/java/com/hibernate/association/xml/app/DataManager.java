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
			
			Set<Company> companies1 = new HashSet<>();
			Company company1 = new Company();
			company1.setCompanyName("Google");
			companies1.add(company1);
			
			Company company2 = new Company();
			company2.setCompanyName("TCS");
			companies1.add(company2);
			
			Company company3 = new Company();
			company3.setCompanyName("Amazon");
			companies1.add(company3);
			
			Job job1 = new Job();
			job1.setJobName("CEO");
			job1.setCompany(companies1);
			
			Set<Company> companies2 = new HashSet<>();
			Company company4 = new Company();
			company4.setCompanyName("Infosys");
			companies2.add(company4);
			
			Company company5 = new Company();
			company5.setCompanyName("CTS");
			companies2.add(company5);
			
			Job job2 = new Job();
			job2.setJobName("CIO");
			job2.setCompany(companies2);
			
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
