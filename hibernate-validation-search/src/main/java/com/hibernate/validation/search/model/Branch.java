package com.hibernate.validation.search.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.hibernate.validation.search.custom.validation.CheckSpecialCharachter;

@Data
public class Branch {

	@Min(6)
	private int branchId;
	
	@NotBlank
	private String branchName;
	
	@NotNull
	@Size(min = 2, max = 5)
	private String branchCode;

	@Pattern(regexp = "^[0-9]*$", message = "Zip code validation failed.")
	private String zipCode;
	
	@CheckSpecialCharachter(value= "[^a-zA-Z0-9*]" ,message ="Special charachters not allowed")
	private String branchHead;
	
}

