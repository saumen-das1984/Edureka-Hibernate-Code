package com.hibernate.hql.annotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "another_employee")
public class AnotherEmployee {
	
	@Id
	@Column(name = "empId")
	private int empId;
	
	@Column(name = "empName")
	private String empName;
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "deptId")
	private String deptId;
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
