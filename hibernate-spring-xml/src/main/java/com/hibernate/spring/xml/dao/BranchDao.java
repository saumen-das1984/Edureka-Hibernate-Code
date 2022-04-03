package com.hibernate.spring.xml.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.spring.xml.model.Branch;


public class BranchDao {
	

	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly = false)
	public void saveBranchDetail(Branch branch){
		hibernateTemplate.save(branch);
	}

	@Transactional(readOnly = false)	
	public void updateBranchDetail(Branch branch){
		System.out.println("For brach id " + branch.getId() + " updating branch name to " + branch.getBranchName());
		hibernateTemplate.update(branch);
	}
	
	@Transactional(readOnly = false)
	public void deleteBranchDetail(Branch branch){
		hibernateTemplate.delete(branch);
	}
	
	public Branch getBranchDetail(int id){
		Branch branch = hibernateTemplate.get(Branch.class, id);
		System.out.println("For brach id " + id + " the corresponidng branch name is " + branch.getBranchName());
		return branch;
	}
	
	
	public List<Branch> getBranchs() {
		List<Branch> branList = new ArrayList<>();
		branList = hibernateTemplate.loadAll(Branch.class);
		for(Branch branch:branList){
			System.out.println("Brach Id " + branch.getId());
			System.out.println("Brach Name " + branch.getBranchName());
		}
		return branList;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


}
