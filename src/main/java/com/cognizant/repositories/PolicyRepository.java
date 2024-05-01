package com.cognizant.repositories;


import org.springframework.data.repository.CrudRepository;
import com.cognizant.entities.Policy;
public interface PolicyRepository extends CrudRepository<Policy,Integer > {
	public Iterable<Policy> findAllByTenure(int tenure);
	public Iterable<Policy> findAllByMaturityAmount(double maturityAmount);
	public Iterable<Policy> findAllByMonthlyPremium(int premiumAmount);
}
