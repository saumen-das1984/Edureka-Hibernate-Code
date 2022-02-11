package com.hibernate.implicit.polymorphism.xml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ContractEmployee extends Employee {

	private int hourlyPayment;
	
}
