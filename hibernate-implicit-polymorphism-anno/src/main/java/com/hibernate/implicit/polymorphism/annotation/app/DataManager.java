package com.hibernate.implicit.polymorphism.annotation.app;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.implicit.polymorphism.annotation.model.ContractEmployee;
import com.hibernate.implicit.polymorphism.annotation.model.PermanentEmployee;
import com.hibernate.implicit.polymorphism.annotation.util.HibernateUtil;



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
			ce1.setEmpName("Bharat");
			
			ContractEmployee ce2 = new ContractEmployee();
			ce2.setEmpName("Antu");
			
			PermanentEmployee pe1 = new PermanentEmployee();
			pe1.setEmpName("Tulsi");
			
			PermanentEmployee pe2 = new PermanentEmployee();
			pe2.setEmpName("Rajib");

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
