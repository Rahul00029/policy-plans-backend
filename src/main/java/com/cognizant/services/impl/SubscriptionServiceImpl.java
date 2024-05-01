package com.cognizant.services.impl;

import java.util.Iterator;
import java.util.Optional;

import com.cognizant.exceptions.DuplicateSubscriptionException;
import com.cognizant.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.dto.SubscriptionDTO;
import com.cognizant.entities.Policy;
import com.cognizant.entities.Subscription;
import com.cognizant.repositories.PolicyRepository;
import com.cognizant.repositories.SubscriptionRepository;

/**
 * This class implements the SubscriptionService interface.
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private PolicyRepository policyRepository;

	/**
	 * This method add a new subscriber into subscription table.
	 * @param policyId
	 * @param subscriptionDTO
	 * @return
	 */
	@Override
	public SubscriptionDTO addNewSubscription(int policyId,SubscriptionDTO subscriptionDTO)
	{
		if(subscriptionDTO==null)
			return null;
		if(subscriptionExists(subscriptionDTO)){
			throw new DuplicateSubscriptionException("Duplicate subscription detected.");
        }
		
		Optional<Policy> optionalPolicy=policyRepository.findById(policyId);
		Policy policy=null;
		if(optionalPolicy.isPresent())
			policy=optionalPolicy.get();
		Subscription subscription=new Subscription();
		subscription.setSubscriptionId(subscriptionDTO.getSubscriptionId());
		subscription.setPolicyId(subscriptionDTO.getPolicyId());
		subscription.setUsername(subscriptionDTO.getUsername());
		subscription.setSubscriptionStatus(subscriptionDTO.getSubscriptionStatus());
		subscription.setMedicalCertificateDocURL(subscriptionDTO.getMedicalCertificateDocURL());
		subscription.setPolicyHolderName(subscriptionDTO.getPolicyHolderName());
		subscription.setRelationToPolicyHolder(subscriptionDTO.getRelationToPolicyHolder());
		subscription.setPolicyHolderIdProofNo(subscriptionDTO.getPolicyHolderIdProofNo());
		subscription.setPolicyHolderIdProofType(subscriptionDTO.getPolicyHolderIdProofType());
		subscription.setPoliciesPolicyId(policy);
		subscription.setSubscriptionDate(subscriptionDTO.getSubscriptionDate());
		Subscription sub=subscriptionRepository.save(subscription);

		if(sub!=null)
			return subscriptionDTO;
		else
			return null;
	}

	/**
	 * This method checks if the subscriber already exist or not. It helps to avoid the duplicate subscriber for the same policy.
	 * @param subscriptionDTO
	 * @return
	 */
	public boolean subscriptionExists(SubscriptionDTO subscriptionDTO) {
		if(findByPolicyHolderIdProofNo(subscriptionDTO.getPolicyHolderIdProofNo()))
			return true;
		Optional<Subscription>optional=subscriptionRepository.findById(subscriptionDTO.getSubscriptionId());
		if(optional.isPresent())
			return true;
		Iterable<Subscription>iterable=subscriptionRepository.findByUsername(subscriptionDTO.getUsername());

		Iterator<Subscription>subIterator=iterable.iterator();
		Subscription subscription=null;
		while(subIterator.hasNext()){
			subscription=subIterator.next();
			if(subscription.getPolicyHolderName().equalsIgnoreCase(subscriptionDTO.getPolicyHolderName()) ||
			(subscription.getRelationToPolicyHolder().equalsIgnoreCase("parent")&&subscriptionDTO.getRelationToPolicyHolder().equalsIgnoreCase("parent"))||
			(subscription.getRelationToPolicyHolder().equalsIgnoreCase("spouse")&&subscriptionDTO.getRelationToPolicyHolder().equalsIgnoreCase("spouse"))||
			(subscription.getRelationToPolicyHolder().equalsIgnoreCase(subscriptionDTO.getRelationToPolicyHolder())&& subscription.getPolicyHolderIdProofNo().equalsIgnoreCase(subscriptionDTO.getPolicyHolderIdProofNo())
			   ))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method finds the subscriber based on PolicyHolderIdProofNo and helps to avoid duplicate subscriber.
	 * @param value
	 * @return
	 */
	@Override
	public boolean findByPolicyHolderIdProofNo(String value) {
		Optional<Subscription>subOptional=subscriptionRepository.findByPolicyHolderIdProofNo(value);
		if(subOptional.isPresent())
			return true;
		return false;
	}
}
