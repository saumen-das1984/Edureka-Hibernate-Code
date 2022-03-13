package com.hibernate.validation.search.custom.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckSpecialValidator implements ConstraintValidator<CheckSpecialCharachter, String> {

	private String value;
	
	@Override
	public void initialize(CheckSpecialCharachter arg0) {
	  this.value=arg0.value();	
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		Pattern pattern = Pattern.compile(value);
		if(arg0 == null) return true;
		Matcher matcher = pattern.matcher(arg0);
	    if(matcher.find()) return false;
		return true;
	}

}
