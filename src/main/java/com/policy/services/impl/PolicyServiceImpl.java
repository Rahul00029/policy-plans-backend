package com.policy.services.impl;

import com.policy.dto.PolicyDTO;
import com.policy.entities.Policy;
import com.policy.entities.PolicyType;
import com.policy.exceptions.PolicyTypeNotFoundException;
import com.policy.repositories.PolicyRepository;
import com.policy.repositories.PolicyTypeRepository;
import com.policy.services.PolicyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the PolicyService interface.
 */
@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private PolicyRepository policyRepository;
    private PolicyTypeRepository policyTypeRepository;

    /**
     * This method add the new policy in Policy table.
     * @param policyDTO
     * @return
     */
    @Override
    public String createNewPolicy(PolicyDTO policyDTO) {
        Policy policy = new Policy();
        PolicyType policyType = null;
        Optional<PolicyType> optionalPolicyType = policyTypeRepository.findByType(policyDTO.getTypeOfPolicy());
        if (optionalPolicyType.isPresent())
            policyType = optionalPolicyType.get();
        else
            throw new PolicyTypeNotFoundException("No policy of this type found");
        double maturityAmount = 0;
        double totalPremiumAmount = policyDTO.getMonthlyPremium() * policyDTO.getTenure();
        if ("Life".equalsIgnoreCase(policyDTO.getTypeOfPolicy())) {
            maturityAmount = totalPremiumAmount + totalPremiumAmount * 0.1;
        } else if ("Health".equalsIgnoreCase(policyDTO.getTypeOfPolicy())) {
            if (policyDTO.getTenure() <= 10) {
                maturityAmount = totalPremiumAmount + totalPremiumAmount * 0.08;
            } else {
                maturityAmount = totalPremiumAmount + totalPremiumAmount * 0.09;
            }
        }

        policy.setPolicyId(policyDTO.getPolicyId());
        policy.setPolicyName(policyDTO.getPolicyName());
        policy.setTenure(policyDTO.getTenure());
        policy.setMaturityAmount(maturityAmount);
        policy.setMonthlyPremium(policyDTO.getMonthlyPremium());
        policy.setPolicyDescription(policyDTO.getPolicyDescription());
        policy.setPolicyTypeId(policyType);

        Policy policyCreated = policyRepository.save(policy);
        if (policyCreated != null)
            return "saved successfully";
        else
            return "Failed to save the policy";
    }

    /**
     * This method search the Policy in Policy table using policy id.
     * @param id
     * @return
     */
    @Override
    public PolicyDTO searchPolicyById(int id) {
        Optional<Policy> optionalPolicy = policyRepository.findById(id);
        PolicyDTO policyDTO = new PolicyDTO();
        if (optionalPolicy.isPresent()) {
        	Policy policy=optionalPolicy.get();
            policyDTO.setPolicyId(policy.getPolicyId());
            policyDTO.setPolicyName(policy.getPolicyName());
            policyDTO.setTenure(policy.getTenure());
            policyDTO.setMaturityAmount(policy.getMaturityAmount());
            policyDTO.setMonthlyPremium(policy.getMonthlyPremium());
            policyDTO.setPolicyDescription(policy.getPolicyDescription());
            policyDTO.setPolicyType(policy.getPolicyTypeId());
            return policyDTO;
        } else
            return null;
    }

    /**
     * This method search the Policy in Policy table using tenure.
     * @param tenure
     * @return
     */
    @Override
    public List<PolicyDTO> searchPolicyByTenure(int tenure) {
        Iterable<Policy> it = policyRepository.findAllByTenure(tenure);
        Iterator<Policy> iterator = it.iterator();
        List<PolicyDTO> policiesDTO = new ArrayList<>();
        while (iterator.hasNext()) {
            Policy policy = iterator.next();
            PolicyDTO policyDTO = new PolicyDTO();
            policyDTO.setPolicyId(policy.getPolicyId());
            policyDTO.setPolicyName(policy.getPolicyName());
            policyDTO.setTenure(policy.getTenure());
            policyDTO.setMaturityAmount(policy.getMaturityAmount());
            policyDTO.setMonthlyPremium(policy.getMonthlyPremium());
            policyDTO.setPolicyDescription(policy.getPolicyDescription());
            policyDTO.setPolicyType(policy.getPolicyTypeId());
            policiesDTO.add(policyDTO);
        }

        return policiesDTO;
    }

    /**
     * This method search the Policy in Policy table using maturity amount.
     * @param maturityAmount
     * @return
     */
    @Override
    public List<PolicyDTO> searchPolicyByMaturityAmount(double maturityAmount) {
        Iterable<Policy> it = policyRepository.findAllByMaturityAmount(maturityAmount);
        Iterator<Policy> iterator=it.iterator();
        List<PolicyDTO> policiesDTO = new ArrayList<>();
        while(iterator.hasNext()) {
        	Policy policy=iterator.next();
            PolicyDTO policyDTO = new PolicyDTO();
            policyDTO.setPolicyId(policy.getPolicyId());
            policyDTO.setPolicyName(policy.getPolicyName());
            policyDTO.setTenure(policy.getTenure());
            policyDTO.setMaturityAmount(policy.getMaturityAmount());
            policyDTO.setMonthlyPremium(policy.getMonthlyPremium());
            policyDTO.setPolicyDescription(policy.getPolicyDescription());
            policyDTO.setPolicyType(policy.getPolicyTypeId());
            policiesDTO.add(policyDTO);
        }

        return policiesDTO;
    }

    /**
     * This method search the Policy in Policy table using monthly Premium amount.
     * @param premiumAmount
     * @return
     */
    @Override
    public List<PolicyDTO> searchPolicyByPremiumAmount(int premiumAmount) {
        Iterable<Policy> it = policyRepository.findAllByMonthlyPremium(premiumAmount);
        Iterator<Policy> iterator=it.iterator();
        List<PolicyDTO> policiesDTO = new ArrayList<>();
        while(iterator.hasNext()){
        	Policy policy=iterator.next();
            PolicyDTO policyDTO = new PolicyDTO();
            policyDTO.setPolicyId(policy.getPolicyId());
            policyDTO.setPolicyName(policy.getPolicyName());
            policyDTO.setTenure(policy.getTenure());
            policyDTO.setMaturityAmount(policy.getMaturityAmount());
            policyDTO.setMonthlyPremium(policy.getMonthlyPremium());
            policyDTO.setPolicyDescription(policy.getPolicyDescription());

            policiesDTO.add(policyDTO);
        }

        return policiesDTO;
    }

}
