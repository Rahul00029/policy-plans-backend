package com.cognizant.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.dto.PolicyDTO;
import com.cognizant.services.PolicyService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PolicyIdValidator implements ConstraintValidator<ValidatePolicyId, Integer> {

	@Autowired
	private PolicyService policyService;
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		PolicyDTO policyDTO= policyService.searchPolicyById(value);
		if(policyDTO==null)
			return true;
		return false;
	}

}
