package com.hibernate.sql.anno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@NamedQueries(  
	    {  
	        @NamedQuery(  
	        name = "namedEmployeeQuery",  
	        query = "from Employee employee where empId=:empId"  
	        )  
	    }  
	) 
public class Employee {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
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
