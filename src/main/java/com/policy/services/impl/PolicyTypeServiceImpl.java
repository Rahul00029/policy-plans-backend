package com.policy.services.impl;

import com.policy.dto.PolicyTypeDTO;
import com.policy.entities.PolicyType;
import com.policy.repositories.PolicyTypeRepository;
import com.policy.services.PolicyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements the PolicyTypeService interface.
 */
@Service
public class PolicyTypeServiceImpl implements PolicyTypeService {

	@Autowired
	private PolicyTypeRepository policyTypeRepositiory;

	/**
	 * This method get all the policy types available in the table.
	 * @return
	 */
	@Override
	public List<PolicyTypeDTO> getAllPolicyTypes() {
		Iterable<PolicyType>it=policyTypeRepositiory.findAll();
		Iterator<PolicyType>iterator=it.iterator();
		List<PolicyTypeDTO>policiesTypeDTO=new ArrayList<>();
		while(iterator.hasNext())
		{
			PolicyType policyType=iterator.next();
			PolicyTypeDTO policyTypeDTO=new PolicyTypeDTO();

			policyTypeDTO.setId(policyType.getId());
			policyTypeDTO.setType(policyType.getType());
			policiesTypeDTO.add(policyTypeDTO);
		}
		
		return policiesTypeDTO;
	}

}
