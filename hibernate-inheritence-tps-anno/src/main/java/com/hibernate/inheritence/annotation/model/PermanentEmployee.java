package com.hibernate.inheritence.annotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "tps_permanent_employee_anno")
@PrimaryKeyJoinColumn(name="emp_id") 
public class PermanentEmployee extends Employee{

	@Column(name = "monthly_payment")
	private int monthlyPayment;
	
}
