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
@Table(name = "tps_contract_employee_anno")
@PrimaryKeyJoinColumn(name="emp_id") 
public class ContractEmployee extends Employee {

	@Column(name = "hourly_payment")
	private int hourlyPayment;
	
}
