package com.policy.services;

import java.util.List;

import com.policy.dto.PolicyTypeDTO;
/**
 * This interface provides the methods to interact with policyType table
 */
public interface PolicyTypeService {
	List<PolicyTypeDTO> getAllPolicyTypes();
}
