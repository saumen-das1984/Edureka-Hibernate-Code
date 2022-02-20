package com.hibernate.criteria.anno.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "another_employee")
//@SecondaryTable(name = "anotheremployee_skills", pkJoinColumns=@PrimaryKeyJoinColumn(name="skillId", referencedColumnName="empId"))
public class AnotherEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="skillId")
	private List<Skill> skills;
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

}
