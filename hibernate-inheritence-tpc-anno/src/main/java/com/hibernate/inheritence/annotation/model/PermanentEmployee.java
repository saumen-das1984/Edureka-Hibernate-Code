package com.hibernate.inheritence.annotation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tpc_permanent_employee")
public class PermanentEmployee extends Employee{

	@Column(name = "monthly_payment")
	private int monthlyPayment;
	
}
