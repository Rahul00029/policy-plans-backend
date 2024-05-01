package com.cognizant.services;

import com.cognizant.dto.PolicyDTO;

import java.util.List;

/**
 * This interface provides the methods to interact with policy table
 */
public interface PolicyService {
	String createNewPolicy(PolicyDTO policyDTO);
	PolicyDTO searchPolicyById(int id);
	List<PolicyDTO> searchPolicyByTenure(int tenure);
	List<PolicyDTO> searchPolicyByMaturityAmount(double maturityAmount);
	List<PolicyDTO> searchPolicyByPremiumAmount(int premiumAmount);
}
