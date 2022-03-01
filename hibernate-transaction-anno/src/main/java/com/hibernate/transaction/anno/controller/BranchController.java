package com.hibernate.transaction.anno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.transaction.anno.model.Branch;
import com.hibernate.transaction.anno.service.BranchService;

@RestController
public class BranchController {
	
	@Autowired(required=true)
	private BranchService branchService;

	
	@RequestMapping(value = "/branches", method = RequestMethod.GET)
	public List<Branch> listBranches() {
		return this.branchService.listBranch();
	}
	
	//For add and update branch both
	@RequestMapping(value= "/branch/add", method = RequestMethod.POST)
	public String addBranch(@RequestBody Branch branch){
		String msg="";
		if(branch.getId() == 0){
			this.branchService.addBranch(branch);
			msg = "Branch Inserted Successfully";
		}else{
			this.branchService.updateBranch(branch);
			msg = "Branch Updated Successfully";
		}
		return msg;
	}
	
	@RequestMapping("/remove/{id}")
    public String removeBranch(@PathVariable("id") int id){
		
        this.branchService.removeBranch(id);
        return "Branch Removed Successfully";
    }
 
}
