package com.hibernate.filter.xml.app;


import java.io.IOException;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;

import com.hibernate.filter.xml.model.Branch;
import com.hibernate.filter.xml.util.HibernateUtil;

public class DataHandler {

	private void populateBranches() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(new Branch("TestBranch", true));
			session.save(new Branch("TestBranch1", true));
			session.save(new Branch("BranchSub", true));
			session.save(new Branch("BranchSub2", true));
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	private void testFilter() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			System.out.println("****** Enabling Filter branchNameFilte******");

			Filter filter = session.enableFilter("branchNameFilter");
			filter.setParameter("branchNameFilterParam", "TestBranch");

			List<Branch> branchList = session.createQuery("from Branch").list();
			for (Branch b : branchList) {
				System.out.println(" Branch Name " + b.getBranchName());
			}

			System.out.println("****** Disabled Filter ******");

			session.disableFilter("branchNameFilter");
		
			branchList = session.createQuery("from Branch").list();
			for (Branch b : branchList) {
				System.out.println(" Branch Name " + b.getBranchName());
			}
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}


	// TODO Drop and create the database in the mysql db before execution
	// drop database bank_db
	// create database bank_db

	public static void main(String[] args) throws IOException {
		try{
		DataHandler dataHandler= new DataHandler();
//		dataHandler.populateBranches();
		dataHandler.testFilter();
		}finally{
			HibernateUtil.shutdown();
		}
	}

}
