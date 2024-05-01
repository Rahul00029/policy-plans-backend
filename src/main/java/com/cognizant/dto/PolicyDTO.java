package com.cognizant.dto;


import com.cognizant.entities.PolicyType;
import com.cognizant.validation.ValidatePolicyId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PolicyDTO {
	@NotNull
	@ValidatePolicyId
	private int policyId;
	@NotNull
	private String policyName;
	@NotNull
	private String policyDescription;
	@Range(min = 1,message = "Tenure must be greater than 0")
	private int tenure;
	@Range(min = 1,message = "Monthly Premium must be greater than 0")
	private int MonthlyPremium;

	private double maturityAmount;
	private String  typeOfPolicy;
	private PolicyType policyType;

}
