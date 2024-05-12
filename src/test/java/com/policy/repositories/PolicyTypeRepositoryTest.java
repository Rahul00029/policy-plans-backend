package com.policy.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import com.policy.entities.PolicyType;
import com.policy.PolicyPlansApplication;

@DataJpaTest
@ContextConfiguration(classes=PolicyPlansApplication.class)
public class PolicyTypeRepositoryTest {
	@Autowired
	private PolicyTypeRepository policyTypeRepo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	
	void testFindAllPositive() {
		PolicyType policyType=new PolicyType();
		policyType.setId(102);
		policyType.setType("Life");
		entityManager.persist(policyType);
		
		Iterable<PolicyType>it=policyTypeRepo.findAll();
		assertTrue(it.iterator().hasNext());	
	}
	
	@Test
	void testFindAllNegative() {
		Iterable<PolicyType>it=policyTypeRepo.findAll();
		assertTrue(it.iterator().hasNext());
	}
	
	@Test
	void testFindByIdPositive() {
		PolicyType policyType=new PolicyType();
		policyType.setId(101);
		policyType.setType("Life");
		entityManager.persist(policyType);
		
		Optional<PolicyType> policy=policyTypeRepo.findById(101);
		assertTrue(policy.isPresent());		
	}
	
	@Test
	void testFindByIdNegative() {
		Optional<PolicyType> policyType=policyTypeRepo.findById(102);
		assertFalse(policyType.isPresent());
	}
	
	@Test
	void testSavePositive() {
		PolicyType policyType=new PolicyType();
		policyType.setId(101);
		policyType.setType("Life");
		policyTypeRepo.save(policyType);
		
		Optional<PolicyType> policy=policyTypeRepo.findById(101);
		assertTrue(policy.isPresent());
	}
	
}
