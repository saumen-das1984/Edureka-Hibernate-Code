package com.hibernate.detached.criteria.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "branch_id")
	private int id;

	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "branch_status")
	private boolean branchStatus;

	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	private Set<BranchAddress> branchAddress;

}
