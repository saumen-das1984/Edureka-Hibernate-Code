package com.hibernate.ogm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Branch {

	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Id 
	private String id;	
    
    @Column(name = "branch_name")
    private String branchName;
	
    @Column(name = "branch_status")
    private boolean branchStatus;

    public Branch(){
    	
    }
    
	public Branch(String branchName, boolean branchStatus) {
		this.branchName = branchName;
		this.branchStatus = branchStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public boolean isBranchStatus() {
		return branchStatus;
	}

	public void setBranchStatus(boolean branchStatus) {
		this.branchStatus = branchStatus;
	}

}
