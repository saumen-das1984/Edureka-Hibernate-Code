package com.hibernate.spring.xml.util;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hibernate.spring.xml.dao.BranchDao;
import com.hibernate.spring.xml.model.Branch;

public class DataHandler {

	private static void populateBranches(BranchDao branchDao){
		branchDao.saveBranchDetail(new Branch("TestBranch"));
		branchDao.saveBranchDetail(new Branch("TestBranch1"));
		branchDao.saveBranchDetail(new Branch("TestBranch2"));
		branchDao.saveBranchDetail(new Branch("TestBranch2"));
		
	}
	//TODO
	//drop database bank_db
	//create database bank_db
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springHibernate.xml");
		BranchDao branchDao = (BranchDao)applicationContext.getBean("branchDetail");
		populateBranches(branchDao);
		branchDao.getBranchs();
		Branch branch=branchDao.getBranchDetail(1);
		branch.setBranchName("New Test branch");
        branchDao.updateBranchDetail(branch);  
	}

}
