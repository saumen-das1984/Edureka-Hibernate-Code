package com.hibernate.criteria.app;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.hibernate.criteria.model.Branch;
import com.hibernate.criteria.model.BranchAddress;
import com.hibernate.criteria.util.HibernateUtil;

public class DataHandler {

	private void populateBranches() {
		System.out.println(" Populating Data ");

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Branch branch = Branch.builder()
					.branchName("TestBranch")
					.branchStatus(true)
					.build();

			BranchAddress branchAddress = BranchAddress.builder()
					.street("branchstreet")
					.state("branchstate")
					.city("branchcity")
					.zipcode(123)
					.build();

			BranchAddress branchAddress1 = BranchAddress.builder()
					.street("branchstreet1")
					.state("branchstate1")
					.city("branchcity1")
					.zipcode(1234)
					.build();

			BranchAddress branchAddress2 = BranchAddress.builder()
					.street("branchstreet1")
					.state("branchstate1")
					.city("branchcity1")
					.zipcode(2345)
					.build();

			Set<BranchAddress> branchAddressess = new HashSet<>();
			branchAddressess.add(branchAddress);
			branchAddressess.add(branchAddress1);
			branchAddressess.add(branchAddress2);
			branch.setBranchAddress(branchAddressess);
			session.save(branch);
			session.save(Branch.builder()
					.branchName("TestBranch2")
					.branchStatus(true)
					.build());
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	private void criteria() {
		System.out.println(" Executing Criteria ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cr = session.createCriteria(Branch.class);
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Branch> branches = cr.list();
			for (Branch branch : branches) {
				System.out.println("Branch Name " + branch.getBranchName());
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

	private void criteriaRestriction() {
		System.out.println(" Executing Criteria Restriction ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cr = session.createCriteria(Branch.class);
			cr.add(Restrictions.eq("branchName", "TestBranch"));
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Branch> branches = cr.list();
			for (Branch branch : branches) {
				System.out.println("Branch Name " + branch.getBranchName());
				for (BranchAddress branchAddress : branch.getBranchAddress()) {
					System.out.println("Branch Address state "
							+ branchAddress.getState());
					System.out.println("Branch Address zipcode "
							+ branchAddress.getZipcode());
				}
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void criteriaProjectionsAnd() {

		System.out.println(" Executing Criteria And Restriction ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cr = session.createCriteria(Branch.class, "branch")
					.createAlias("branch.branchAddress", "bd",
							JoinType.INNER_JOIN);

			Criterion c1 = Restrictions.eq("branch.branchName", "TestBranch");
			Criterion c2 = Restrictions.eq("bd.city", "branchcity1");
			cr.add(Restrictions.and(c1, c2));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("branch.branchName"));
			projectionList.add(Projections.property("bd.city"));
			cr.setProjection(projectionList);
			List branches = cr.list();
			for (Object branch : branches) {
				Object[] resltBranches = (Object[]) branch;
				if (resltBranches.length <= 2) {
					System.out.println(" Branch Name " + resltBranches[0]);
					System.out.println(" Branch City " + resltBranches[1]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void criteriaProjectionsOr() {

		System.out.println(" Executing Criteria OR Restriction ");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cr = session.createCriteria(Branch.class, "branch")
					.createAlias("branch.branchAddress", "bd",
							JoinType.INNER_JOIN);

			Criterion c1 = Restrictions.eq("branch.branchName", "TestBranch");
			Criterion c2 = Restrictions.eq("bd.city", "branchcity1");
			cr.add(Restrictions.or(c1, c2));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("branch.branchName"));
			projectionList.add(Projections.property("bd.city"));
			cr.setProjection(projectionList);
			List branches = cr.list();
			for (Object branch : branches) {
				Object[] resltBranches = (Object[]) branch;
				if (resltBranches.length <= 3) {
					System.out.println(" Branch Name " + resltBranches[0]);
					System.out.println(" Branch City " + resltBranches[1]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	private void criteriaOrderBy() {
		System.out.println(" Executing Criteria Order by");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cr = session.createCriteria(Branch.class)
					      .addOrder(Order.desc("id")) ;
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Branch> branches = cr.list();
			for (Branch branch : branches) {
				System.out.println("Branch ID " + branch.getId());
				System.out.println("Branch Name " + branch.getBranchName());
				for (BranchAddress branchAddress : branch.getBranchAddress()) {
					System.out.println("Branch Address state "
							+ branchAddress.getState());
					System.out.println("Branch Address zipcode"
							+ branchAddress.getZipcode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void criteriaRestrictResults() {
		System.out.println(" Executing Criteria Max Results");
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria cr = session.createCriteria(Branch.class)
					      .addOrder(Order.desc("id")) 
					      .setMaxResults(1);
			List<Branch> branches = cr.list();
			for (Branch branch : branches) {
				System.out.println("Branch ID " + branch.getId());
				System.out.println("Branch Name " + branch.getBranchName());
				for (BranchAddress branchAddress : branch.getBranchAddress()) {
					System.out.println("Branch Address state "
							+ branchAddress.getState());
					System.out.println("Branch Address zipcode"
							+ branchAddress.getZipcode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	// TODO Drop and create the database in the mysql db before execution
	// drop database bank_db
	// create database bank_db
	public static void main(String[] args) throws IOException {
		try {
			DataHandler dataHandler = new DataHandler();
			//dataHandler.populateBranches();
			//dataHandler.criteria();
			//dataHandler.criteriaRestriction();
			//dataHandler.criteriaProjectionsAnd();
			dataHandler.criteriaProjectionsOr();
			//dataHandler.criteriaOrderBy();
			//dataHandler.criteriaRestrictResults();

		} finally {
			HibernateUtil.shutdown();
		}
	}

}
