package com.hibernate.inheritence.xml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employee {
	private int empId;
	private String empName;
}
