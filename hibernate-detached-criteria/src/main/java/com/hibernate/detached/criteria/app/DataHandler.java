package com.hibernate.detached.criteria.app;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.hibernate.detached.criteria.model.Branch;
import com.hibernate.detached.criteria.model.BranchAddress;
import com.hibernate.detached.criteria.util.HibernateUtil;

public class DataHandler {

	private void populateBranches() {
		System.out.println(" Populating Data ");

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Branch branch = Branch.builder()
					.branchName("TestBranch4")
					.branchStatus(true)
					.build();

			BranchAddress branchAddress = BranchAddress.builder()
					.street("branchstreet4")
					.state("branchstate4")
					.city("branchcity4")
					.zipcode(123)
					.build();

			BranchAddress branchAddress1 = BranchAddress.builder()
					.street("branchstreet5")
					.state("branchstate5")
					.city("branchcity5")
					.zipcode(1234)
					.build();

			BranchAddress branchAddress2 = BranchAddress.builder()
					.street("branchstreet5")
					.state("branchstate5")
					.city("branchcity5")
					.zipcode(2345)
					.build();
			Set<BranchAddress> branchAddressess = new HashSet<>();
			branchAddressess.add(branchAddress);
			branchAddressess.add(branchAddress1);
			branchAddressess.add(branchAddress2);
			branch.setBranchAddress(branchAddressess);
			session.save(branch);
			session.save(Branch.builder()
					.branchName("TestBranch3")
					.branchStatus(true)
					.build());
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	private void detachedCriteria() {
		System.out.println("Executing Detached Criteria Without Session");
		HibernateTemplate ht = new HibernateTemplate(
				HibernateUtil.getSessionFactory());
		DetachedCriteria criteria = DetachedCriteria.forClass(Branch.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		try {
			List<Branch> branches = (List<Branch>) ht.findByCriteria(criteria);
			for (Branch branch : branches) {
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
		}
	}

	@SuppressWarnings("unchecked")
	private void detachedCriteriaRestriction() {
		System.out.println(" Executing Detached Criteria Restriction Without Session");
		HibernateTemplate ht = new HibernateTemplate(
				HibernateUtil.getSessionFactory());
		DetachedCriteria criteria = DetachedCriteria.forClass(Branch.class);
		criteria.add(Restrictions.eq("branchName", "TestBranch"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		try {
			List<Branch> branches = (List<Branch>) ht.findByCriteria(criteria);
			for (Branch branch : branches) {
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
		}
	}

	private void detachedCriteriaProjectionsAnd() {

		System.out.println(" Executing Detached Criteria And Restriction ");
		HibernateTemplate ht = new HibernateTemplate(HibernateUtil.getSessionFactory());

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Branch.class,"branch")
								.createAlias("branch.branchAddress", "bd",
							JoinType.INNER_JOIN);

			Criterion c1 = Restrictions.eq("branch.branchName", "TestBranch");
			Criterion c2 = Restrictions.eq("bd.city", "branchcity1");
			criteria.add(Restrictions.and(c1, c2));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("branch.branchName"));
			projectionList.add(Projections.property("bd.city"));
			criteria.setProjection(projectionList);
			List branches = ht.findByCriteria(criteria);
			for (Object branch : branches) {
				Object[] resltBranches = (Object[]) branch;
				if (resltBranches.length <= 2) {
					System.out.println(" Branch Name " + resltBranches[0]);
					System.out.println(" Branch City " + resltBranches[1]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	// TODO Drop and create the database in the mysql db before execution
	// drop database bank_db
	// create database bank_db
	public static void main(String[] args) throws IOException {
		try {
			DataHandler dataHandler = new DataHandler();
//			dataHandler.populateBranches();
//			dataHandler.detachedCriteria();
//			dataHandler.detachedCriteriaRestriction();
		    dataHandler.detachedCriteriaProjectionsAnd();
		    //One can execute all other criteria examples in the same way
		} finally {
			HibernateUtil.shutdown();
		}
	}

}
