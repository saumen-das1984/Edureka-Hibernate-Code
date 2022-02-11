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
//@Table(name ="reg_employee_anno")
@DiscriminatorValue(value="reg_emp")
public class PermanentEmployee extends Employee{

	@Column(name = "monthly_payment")
	private int monthlyPayment;
	
}
