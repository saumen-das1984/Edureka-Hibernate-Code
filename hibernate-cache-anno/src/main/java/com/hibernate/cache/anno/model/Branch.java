package com.hibernate.cache.anno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "branch_id")
	private int id;

	@Column(name = "branch_name")
	@NonNull
	private String branchName;
	
	@Column(name = "branch_status")
	@NonNull
	private boolean branchStatus;
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
