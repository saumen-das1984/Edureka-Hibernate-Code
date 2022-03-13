package com.hibernate.validation.search.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.hibernate.validation.search.model.Branch;

public class ValidationUtil {

	Validator validator;
	
	public ValidationUtil(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
    
	private int validateBranch(Branch branch){
		Set<ConstraintViolation<Branch>> constraintViolations = validator
				.validate(branch);
		if(constraintViolations.size() == 0){
			System.out.println("Validation success ");
			return 0;
		}
		for (ConstraintViolation<Branch> errors : constraintViolations) {
			System.out.println("Property "
					+ errors.getPropertyPath().toString() + " runtime value "
					+ errors.getInvalidValue() + ". Constraint violation: "
					+ errors.getMessage());
		}
		return constraintViolations.size();
	}
	
	public static void main(String args[]){
	   ValidationUtil validationUtil=new ValidationUtil();
	   Branch branch=new Branch();
	   System.out.println("Validating Bean");
	   validationUtil.validateBranch(branch);
	   
	   branch.setBranchId(7);
	   System.out.println();
	   System.out.println("Validating Bean");
	   validationUtil.validateBranch(branch);
	   
	   branch.setBranchName("Bracn Name");
	   branch.setBranchCode("123");
	   branch.setZipCode("abcd");
	   System.out.println();
	   System.out.println("Validating Bean");
	   validationUtil.validateBranch(branch);
	   
	   branch.setZipCode("12334");
	   branch.setBranchHead("&$");
	   System.out.println();
	   System.out.println("Validating Bean");
	   validationUtil.validateBranch(branch);
	   
	   branch.setZipCode("12334");
	   branch.setBranchHead("BranchMaster");
	   System.out.println();
	   System.out.println("Validating Bean");
	   validationUtil.validateBranch(branch);
	   
	}
}
