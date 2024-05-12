package com.policy.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import com.policy.entities.Subscription;
import com.policy.PolicyPlansApplication;

@DataJpaTest
@ContextConfiguration(classes=PolicyPlansApplication.class)
class SubscriptionRepositoryTest {
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testFindAllPositive() {
		Subscription sub=new Subscription();
		sub.setSubscriptionId(2);
		sub.setPolicyId(2);
		sub.setSubscriptionDate("2023-02-23");
		sub.setPolicyHolderName("Ram");
		sub.setUsername("Syam");
		sub.setSubscriptionStatus("active");
//		sub.setPoliciesPolicyId(1);
		sub.setMedicalCertificateDocURL("www.googleDocs.453547sfdfh");
		sub.setRelationToPolicyHolder("Brother");
		sub.setPolicyHolderIdProofType("Aadhar");
		sub.setPolicyHolderIdProofNo("78876852");
		entityManager.persist(sub);
		Iterable<Subscription> it=subscriptionRepository.findAll();
		assertTrue(it.iterator().hasNext());		
	}
	
	@Test
	void testFindAllNegative() {
		Iterable<Subscription>it=subscriptionRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	
	@Test
	void testFindByIdPositive() {
		Subscription sub=new Subscription();
		sub.setSubscriptionId(2);
		sub.setPolicyId(2);
		sub.setSubscriptionDate("2023-02-23");
		sub.setPolicyHolderName("Ram");
		sub.setUsername("Syam");
		sub.setSubscriptionStatus("active");

		sub.setMedicalCertificateDocURL("www.googleDocs.453547sfdfh");
		sub.setRelationToPolicyHolder("Brother");
		sub.setPolicyHolderIdProofType("Aadhar");
		sub.setPolicyHolderIdProofNo("78876852");
		
		entityManager.persist(sub);
		Optional<Subscription> subOptional=subscriptionRepository.findById(2);
		assertTrue(subOptional.isPresent());		
	}
	
	@Test
	void testFindByIdNegative() {
		Optional<Subscription> subOptional=subscriptionRepository.findById(2);
		assertFalse(subOptional.isPresent());
	}
	
	@Test
	void testSavePositive() {
		Subscription sub=new Subscription();
		sub.setSubscriptionId(2);
		sub.setPolicyId(2);
		sub.setSubscriptionDate("2023-02-23");
		sub.setPolicyHolderName("Ram");
		sub.setUsername("Syam");
		sub.setSubscriptionStatus("active");

		sub.setMedicalCertificateDocURL("www.googleDocs.453547sfdfh");
		sub.setRelationToPolicyHolder("Brother");
		sub.setPolicyHolderIdProofType("Aadhar");
		sub.setPolicyHolderIdProofNo("78876852");
		
		subscriptionRepository.save(sub);
		
		Optional<Subscription> subOptional=subscriptionRepository.findById(2);
		assertTrue(subOptional.isPresent());
	}

}
