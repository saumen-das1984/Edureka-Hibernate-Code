package com.hibernate.inheritence.xml.app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.inheritence.xml.model.ContractEmployee;
import com.hibernate.inheritence.xml.model.PermanentEmployee;
import com.hibernate.inheritence.xml.util.HibernateUtil;


public class DataManager {
	public static void main(String[] args) {
		saveJob();
	}
	
	private static void saveJob() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			ContractEmployee ce1 = new ContractEmployee();
			ce1.setEmpName("Rahul");
			
			ContractEmployee ce2 = new ContractEmployee();
			ce2.setEmpName("Raju");
			
			PermanentEmployee pe1 = new PermanentEmployee();
			pe1.setEmpName("Chahar");
			
			PermanentEmployee pe2 = new PermanentEmployee();
			pe2.setEmpName("Chahal");
			
			session.save(ce1);
			session.save(ce2);
			session.save(pe1);
			session.save(pe2);
			
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
