package com.hibernate.nativesql.anno.app;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.hibernate.nativesql.anno.model.Branch;
import com.hibernate.nativesql.anno.model.BranchAddress;
import com.hibernate.nativesql.anno.util.HibernateUtil;

public class DataHandler {

	private void populateBranches() {
		System.out.println(" Populating Data ");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Branch branch = Branch.builder()
					.branchName("TestBranch7")
					.branchStatus(true)
					.build();

			BranchAddress branchAddress = BranchAddress.builder()
					.street("branchstreet7")
					.state("branchstate7")
					.city("branchcity7")
					.zipcode(123)
					.build();

			BranchAddress branchAddress1 = BranchAddress.builder()
					.street("branchstreet8")
					.state("branchstate8")
					.city("branchcity8")
					.zipcode(1234)
					.build();

			BranchAddress branchAddress2 = BranchAddress.builder()
					.street("branchstreet8")
					.state("branchstate8")
					.city("branchcity8")
					.zipcode(2345)
					.build();
			
			Set<BranchAddress> branchAddressess=new HashSet<>();
			branchAddressess.add(branchAddress);
			branchAddressess.add(branchAddress1);
			branchAddressess.add(branchAddress2);
			branch.setBranchAddress(branchAddressess);
			session.save(branch);
			session.save(Branch.builder()
					.branchName("TestBranch9")
					.branchStatus(true)
					.build());
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	private void scalarQuery() {
		System.out.println(" Executing Scalar Query ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String sql = "select branch_name from branch";
			SQLQuery query = session.createSQLQuery(sql);
			List results = query.list();
			for(Object object:results){
				System.out.println("Branch name " + object.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private void entityQuery() {
		System.out.println(" Executing Entity Query ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String sql = "select * from branch";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Branch.class);
			List<Branch> results = query.list();
			for(Branch branch : results){
				System.out.println(" Bracnh Id " + branch.getId());
				System.out.println(" Bracnh Name " + branch.getBranchName());
				for (BranchAddress branchAddress : branch.getBranchAddress()) {
					System.out.println("Branch Address state "
							+ branchAddress.getState());
					System.out.println("Branch Address zipcode "
							+ branchAddress.getZipcode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void namedQuery() {
		
		System.out.println(" Executing Named Query ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String sql = "select * from branch_address where zipcode <= :zip";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(BranchAddress.class);
			query.setParameter("zip", 1234);
			List<BranchAddress> results = query.list();
			for(BranchAddress branchAddress:results){
				System.out.println("Branch State " + branchAddress.getState());
				System.out.println("Branch Zip " + branchAddress.getZipcode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	
	//TODO Drop and create the database in the mysql db before execution
	// drop database bank_db
	// create database bank_db
	public static void main(String[] args) throws IOException {
		try{
			DataHandler dataHandler= new DataHandler();
//			dataHandler.populateBranches();
		    dataHandler.scalarQuery();  
		    dataHandler.entityQuery();
		    dataHandler.namedQuery();
		}finally{
			HibernateUtil.shutdown();
		}
	}

}
