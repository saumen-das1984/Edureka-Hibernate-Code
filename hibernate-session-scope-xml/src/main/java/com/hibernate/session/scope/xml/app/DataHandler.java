package com.hibernate.session.scope.xml.app;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.session.scope.xml.model.Branch;
import com.hibernate.session.scope.xml.model.BranchAddress;
import com.hibernate.session.scope.xml.util.HibernateUtil;

public class DataHandler {

	// TODO Drop and create the database in the mysql db before execution
	// drop database bank_db
	// create database bank_db
	public static void main(String[] args) throws IOException {
		try {
			 DataHandler datahandler = new DataHandler();
			 datahandler.sessionPerOperation();
			 datahandler.sessionPerRequest();
//			 datahandler.insertBranchAddress();
			 datahandler.sessionPerRequestWithDeatached(10);
		} finally {
			HibernateUtil.shutdown();
		}
	}
	

	public void sessionPerOperation() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			System.out.println("Session per operation");
			transaction = session.beginTransaction();
			Branch branch = Branch.builder()
					.branchName("Test1 Branch")
					.branchStatus(true)
					.build();
			session.save(branch);
			transaction.commit();
			System.out.println("");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void sessionPerRequest() {
		System.out.println("Session per request");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Branch branch = Branch.builder()
					.branchName("Test2 Branch")
					.branchStatus(true)
					.build();
			session.save(branch);
			branch.setBranchStatus(false);
			session.save(branch);
			transaction.commit();
			System.out.println("");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void insertBranchAddress() {
		System.out.println("Insert Branch Address");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Branch branch = Branch.builder()
					.branchName("Sub Branch")
					.branchStatus(true)
					.build();
			BranchAddress branchAddress = BranchAddress.builder()
					.street("branchstreet10")
					.state("branchstate10")
					.city("branchcity10")
					.zipcode(12310)
					.build();
			BranchAddress branchAddress1 = BranchAddress.builder()
					.street("branchstreet11")
					.state("branchstate11")
					.city("branchcity11")
					.zipcode(12311)
					.build();
			Set<BranchAddress> branchAddressess = new HashSet<>();
			branchAddressess.add(branchAddress);
			branchAddressess.add(branchAddress1);
			branch.setBranchAddress(branchAddressess);
			session.save(branch);
			transaction.commit();
			System.out.println("");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void sessionPerRequestWithDeatached(int branchId) {
		System.out.println("Session per request detached");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Branch fetchBranch = (Branch) session.get(Branch.class, branchId);
			session.close();
			try {
				for (BranchAddress branchAddressDetails : fetchBranch
						.getBranchAddress()) {
					System.out.println(branchAddressDetails.getState());
				}
			} catch (HibernateException e) {
				System.out
						.println("Session is closed and entity pusehed to detached state. Hence not able to fetch branchAddress");
			}
			// using it oustside session context
			session = HibernateUtil.getSessionFactory().openSession();
			Branch detachedBranch = (Branch) session
					.get(Branch.class, branchId);
			Hibernate.initialize(detachedBranch.getBranchAddress());
			session.close();
			for (BranchAddress branchAddressDetails : detachedBranch
					.getBranchAddress()) {
				System.out.println(branchAddressDetails.getState());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
