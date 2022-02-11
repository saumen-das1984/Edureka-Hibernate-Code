package com.hibernate.implicit.polymorphism.annotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "ipm_permanent_employee_anno")
public class PermanentEmployee implements Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "emp_id")
	private int empId;
	
	@Column (name = "emp_name")
	private String empName;
	
	@Column(name = "monthly_payment")
	private int monthlyPayment;
	
}
