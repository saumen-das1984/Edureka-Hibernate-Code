package com.hibernate.inheritence.annotation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
//@Table(name ="con_employee_anno")
@DiscriminatorValue(value="con_emp")
public class ContractEmployee extends Employee {

	@Column(name = "hourly_payment")
	private int hourlyPayment;
	
}
