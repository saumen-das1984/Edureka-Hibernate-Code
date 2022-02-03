package com.hibernate.config.annotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="branch")
public class Branch {
	
	@Id
	private int  branchId;
	@Column (name = "branchName")
	private String branchName;
	@Column (name = "branchStatus")
	private boolean branchStatus;

}
