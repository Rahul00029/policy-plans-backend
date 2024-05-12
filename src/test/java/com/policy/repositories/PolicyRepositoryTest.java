package com.policy.repositories;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.policy.entities.Policy;
import com.policy.PolicyPlansApplication;

@DataJpaTest
@ContextConfiguration(classes=PolicyPlansApplication.class)
class PolicyRepositoryTest {
	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testFindAllPositive() {
		Policy policy=new Policy();
		policy.setPolicyId(8);
		policy.setPolicyName("LIC");
		policy.setMaturityAmount(500.0);
		policy.setMonthlyPremium(250);
		policy.setPolicyDescription("LIC k sath bhi LIC ke baad bhi");
		policy.setTenure(20000);
		
		entityManager.persist(policy);
		Iterable<Policy>it=policyRepository.findAll();
		Iterator<Policy> itt=it.iterator();
		itt.next();
		assertTrue(itt.hasNext());
	}
	
	@Test
	void testFindAllNegative() {
		Iterable<Policy>it=policyRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	
	@Test
	void testFindByIdPositive() {
		Policy policy=new Policy();
		policy.setPolicyId(1);
		policy.setPolicyName("LIC");
		policy.setMaturityAmount(500.0);
		policy.setMonthlyPremium(250);
		policy.setPolicyDescription("LIC k sath bhi LIC ke baad bhi");
		policy.setTenure(20000);
		
		entityManager.persist(policy);
		Optional<Policy> policyOptional=policyRepository.findById(1);
		assertTrue(policyOptional.isPresent());
	}
	
	@Test
	void testFindByIdNegative() {
		Optional<Policy> policy=policyRepository.findById(-1);
		assertFalse(policy.isPresent());
	}
	
	@Test
	void testSavePositive() {
		Policy policy=new Policy();
		policy.setPolicyId(1);
		policy.setPolicyName("LIC");
		policy.setMaturityAmount(500.0);
		policy.setMonthlyPremium(250);
		policy.setPolicyDescription("LIC k sath bhi LIC ke baad bhi");
		policy.setTenure(20000);
		
		policyRepository.save(policy);
		
		Optional<Policy> policyOptional=policyRepository.findById(1);
		assertTrue(policyOptional.isPresent());
	}


}
