package com.policy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.policy.services.SubscriptionService;
@Component
public class IdProofNoValidator implements ConstraintValidator<ValidateIdProofNo,String>{

	@Autowired
	private SubscriptionService subscriptionService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean subscriberFound=subscriptionService.findByPolicyHolderIdProofNo(value);
		if(subscriberFound) {
			return false;
		}
		return true;
	}
	
}
