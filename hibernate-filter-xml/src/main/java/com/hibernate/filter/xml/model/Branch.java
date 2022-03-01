package com.hibernate.filter.xml.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
//@Builder
@FilterDef(name="branchNameFilter", parameters=@ParamDef( name="branchNameFilterParam", type="java.lang.String" ) )
@Filter(name = "branchNameFilter", condition = "branch_name = :branchNameFilterParam")
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
