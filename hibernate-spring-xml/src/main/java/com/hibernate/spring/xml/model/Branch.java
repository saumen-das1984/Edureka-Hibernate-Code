package com.hibernate.spring.xml.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Branch {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id 
	private int id;	
    
    @Column(name = "branch_name")
    @NonNull
    private String branchName;
	
    @Column(name = "branch_status")
    private boolean branchStatus;
    
}
