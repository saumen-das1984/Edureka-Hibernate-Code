package com.hibernate.hql.annotation.app;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.hql.annotation.model.Branch;
import com.hibernate.hql.annotation.model.BranchAddress;
import com.hibernate.hql.annotation.util.HibernateUtil;

@SuppressWarnings({ "deprecation", "unused", "rawtypes" })
public class DataHandler {

	private void populateBranches() {
		System.out.println(" Populating Data ");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Branch branch=Branch.builder()
					.branchName("TestBranch")
					.branchStatus(true)
					.build();
			
			Branch branch2= Branch.builder()
					.branchName("TestBranch2")
					.branchStatus(true)
					.build();
			
			BranchAddress branchAddress=BranchAddress.builder()
					.street("branchstreet")
					.city("branchcity")
					.state("branchstate")
					.id(123)
					.build();
			
			BranchAddress branchAddress1=BranchAddress.builder()
					.street("branchstreet")
					.city("branchcity")
					.state("branchstate")
					.id(123)
					.build();

			BranchAddress branchAddress2=BranchAddress.builder()
					.street("branchstreet")
					.city("branchcity")
					.state("branchstate")
					.id(123)
					.build();
			
			Set<BranchAddress> branchAddressess=new HashSet<>();
			branchAddressess.add(branchAddress);
			branchAddressess.add(branchAddress1);
			branchAddressess.add(branchAddress2);
			branch.setBranchAddress(branchAddressess);
			session.save(branch);
			session.save(branch2);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	private void hqlSelectJoin() {
		System.out.println(" Executing HQL Polymorphic Selet Join ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String fromQuery="select b.branchName,bd.street from Branch b join b.branchAddress bd";
			Query query=session.createQuery(fromQuery);
			List results=query.list();
			Object[] resultObjects=results.toArray();
			for(int i=0;i<resultObjects.length;i++){
				Object[] objects=(Object[])resultObjects[i];
				if(objects.length <=2 ){
					System.out.println("Branch Name "+ objects[0] );
					System.out.println("Branch Address street " + objects[1] );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void hqlSubQuery() {
		System.out.println(" Executing HQL Sub Query ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String fromQuery="select b.id,b.branchName from Branch b where b.id not in(select b.id from Branch b join b.branchAddress bd)";
			Query query=session.createQuery(fromQuery);
			List results=query.list();
			Object[] resultObjects=results.toArray();
			for(int i=0;i<resultObjects.length;i++){
				Object[] objects=(Object[])resultObjects[i];
				if(objects.length <=2 ){
					System.out.println("Branch id "+ objects[0] );
					System.out.println("Branch Name " + objects[1] );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	private void hqlGroupBy() {
		System.out.println(" Executing HQL groupBy");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String fromQuery="select bd.zipcode,count(bd.zipcode) from BranchAddress bd group by bd.zipcode";
			Query query=session.createQuery(fromQuery);
			List results=query.list();
			Object[] resultObjects=results.toArray();
			for(int i=0;i<resultObjects.length;i++){
				Object[] objects=(Object[])resultObjects[i];
				if(objects.length <=2 ){
					System.out.println("Zip Code - "+ objects[0] +" Count - " + objects[1] );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private void hqlAggregations() {
		System.out.println(" Executing HQL Aggregations");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String fromQuery="select max(bd.zipcode),min(bd.zipcode),count(bd.zipcode),avg(bd.zipcode),sum(bd.zipcode) from Branch b join b.branchAddress bd";
			Query query=session.createQuery(fromQuery);
			List results=query.list();
			Object[] resultObjects=results.toArray();
			for(int i=0;i<resultObjects.length;i++){
				Object[] objects=(Object[])resultObjects[i];
				if(objects.length <=5 ){
					System.out.println("Zip Code Max "+ objects[0] );
					System.out.println("Zip Code Min "+ objects[1] );
					System.out.println("Zip Code Count "+ objects[2] );
					System.out.println("Zip Code Avg "+ objects[3] );
					System.out.println("Zip Code Sum "+ objects[4] );
				}
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
			dataHandler.populateBranches();
			dataHandler.hqlSelectJoin();
			dataHandler.hqlSubQuery();
			dataHandler.hqlAggregations();
			dataHandler.hqlGroupBy();
			
		}finally{
			HibernateUtil.shutdown();
		}
	}

}