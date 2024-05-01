package com.cognizant.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.cognizant.entities.Subscription;


public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	Iterable<Subscription> findByUsername(String username);
	Optional<Subscription> findByPolicyHolderIdProofNo(String id);
}
