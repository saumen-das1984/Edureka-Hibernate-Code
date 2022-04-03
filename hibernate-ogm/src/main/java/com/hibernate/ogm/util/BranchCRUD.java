package com.hibernate.ogm.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.ogm.model.Branch;

public class BranchCRUD {

	private EntityManager em;

	public BranchCRUD() {
		em = Persistence.createEntityManagerFactory("ogm-mongodb").createEntityManager();
	}

	public void insert(Branch branch) {
		System.out.println("inserting branch");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(branch);
		em.flush();
		tx.commit();
		System.out.println("branch inserted successfully");
	}

	
	public void read(String branchId) {
		Branch branch=em.find(Branch.class,branchId );
		System.out.println("branch read successfully");
		System.out.println("Branch Name " + branch.getBranchName());
		System.out.println("Branch Status "+ branch.isBranchStatus());
	}
	
	public void updateBrnach(String branchId) {
		Branch branch=em.find(Branch.class,branchId );
		branch.setBranchName("Changed Branch Name");
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(branch);
		em.flush();
		tx.commit();
		System.out.println("branch updated successfully");
	}

	public void removeBrnach(String branchId) {
		Branch branch=em.find(Branch.class,branchId );
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(branch);
		em.flush();
		tx.commit();
		System.out.println("branch deleted successfully");

	}
	
	public static void main(String[] args) {
		BranchCRUD branchCRUD = new BranchCRUD();
//		branchCRUD.insert(new Branch("Sub Branch4", true));
//		branchCRUD.insert(new Branch("Sub Branch5", true));
		String branchId="5d085210-6ed6-4b9f-aca3-1b37e4cc58c8";
		branchCRUD.read(branchId);
		branchCRUD.updateBrnach(branchId);
		branchCRUD.removeBrnach(branchId);
	}
}
