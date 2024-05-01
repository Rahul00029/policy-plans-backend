package com.cognizant.services;

import com.cognizant.dto.SubscriptionDTO;
/**
 * This interface provides the methods to interact with subscription table
 */
public interface SubscriptionService {
	SubscriptionDTO addNewSubscription(int policyId, SubscriptionDTO subscriptionDTO);
	boolean findByPolicyHolderIdProofNo(String value);
}
