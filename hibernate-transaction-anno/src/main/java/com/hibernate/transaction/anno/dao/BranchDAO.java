package com.hibernate.transaction.anno.dao;

import java.util.List;

import com.hibernate.transaction.anno.model.Branch;


public interface BranchDAO {

	public void addBranch(Branch branch);
	public void updateBranch(Branch branch);
	public List<Branch> listBranch();
	public Branch getBranchById(int id);
	public void removeBranch(int id);
}
