package com.hibernate.cache.xml.app;

import java.io.IOException;

import org.hibernate.Session;

import com.hibernate.cache.xml.model.Branch;
import com.hibernate.cache.xml.util.HibernateUtil;

public class DataHandler {


	private void populateBranches() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(new Branch("TestBranch11", true));
			session.save(new Branch("TestBranch12", true));
			session.save(new Branch("TestBranch13", true));
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void testCache(int branchId) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			//fetching branch first time from database
			Branch branch = (Branch) session.load(Branch.class, new Integer(branchId));
			System.out.println("Fetching branch from database");
			System.out.println(branch.getBranchName());
			
			//fetching branch but this time it fetches from first level cache
			branch = (Branch) session.load(Branch.class, new Integer(branchId));
			System.out.println("Fetching branch from first level cache");
			System.out.println(branch.getBranchName());
			
			session.close();
			
			Session anotherSession = HibernateUtil.getSessionFactory().openSession();
			//fetching branch but this time it comes form secondary cache ,hence no database hit
			
			branch = (Branch) anotherSession.load(Branch.class, new Integer(branchId));
			System.out.println("Fetching branch from second level cache");
			System.out.println(branch.getBranchName());
			
			anotherSession.close();		
	}


	// TODO Drop and create the database in the mysql db before execution
	// drop database bank_db
	// create database bank_db
	public static void main(String[] args) throws IOException {
		try{
		DataHandler dataHandler= new DataHandler();
//		dataHandler.populateBranches();
		dataHandler.testCache(1);
		}finally{
			HibernateUtil.shutdown();
		}
	}

}
