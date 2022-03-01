package com.hibernate.transaction.anno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.transaction.anno.dao.BranchDAO;
import com.hibernate.transaction.anno.model.Branch;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired
	private BranchDAO batchDAO;

//	public void setBranchDAO(BranchDAO batchDAO) {
//		this.batchDAO = batchDAO;
//	}

	@Override
	@Transactional
	public void addBranch(Branch p) {
		this.batchDAO.addBranch(p);
	}

	@Override
	@Transactional
	public void updateBranch(Branch p) {
		this.batchDAO.updateBranch(p);
	}

	@Override
	@Transactional
	public List<Branch> listBranch() {
		return this.batchDAO.listBranch();
	}

	@Override
	@Transactional
	public Branch getBranchById(int id) {
		return this.batchDAO.getBranchById(id);
	}

	@Override
	@Transactional
	public void removeBranch(int id) {
		this.batchDAO.removeBranch(id);
	}

}
