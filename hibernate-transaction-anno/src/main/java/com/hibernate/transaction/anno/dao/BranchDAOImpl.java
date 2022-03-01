package com.hibernate.transaction.anno.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.transaction.anno.model.Branch;


@Repository
public class BranchDAOImpl implements BranchDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BranchDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
//	public void setSessionFactory(SessionFactory sf){
//		this.sessionFactory = sf;
//	}

	@Override
	public void addBranch(Branch batch) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(batch);
		logger.info("batch saved successfully");
	}

	@Override
	public void updateBranch(Branch branch) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(branch);
		logger.info("branch updated successfully");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Branch> listBranch() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Branch> branchList = session.createQuery("from Branch").list();
		for(Branch b : branchList){
			logger.info(" Branch Name"+ b.getBranchName());
		}
		return branchList;
	}

	@Override
	public Branch getBranchById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Branch branch = (Branch) session.load(Branch.class, id);
		logger.info("Branch loaded successfully");
		return branch;
	}

	@Override
	public void removeBranch(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Branch branch = (Branch) session.load(Branch.class, id);
	    session.delete(branch);
		logger.info("Branch deleted successfully");
	}

}
