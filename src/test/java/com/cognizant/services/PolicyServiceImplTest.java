package com.cognizant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cognizant.dto.PolicyDTO;
import com.cognizant.entities.Policy;
import com.cognizant.entities.PolicyType;
import com.cognizant.repositories.PolicyRepository;
import com.cognizant.repositories.PolicyTypeRepository;
import com.cognizant.services.impl.PolicyServiceImpl;

class PolicyServiceImplTest {
	@Mock
	private PolicyRepository policyRepository;
	@Mock
	private PolicyTypeRepository policyTypeRepository;
	@InjectMocks
	private PolicyServiceImpl policyService;

	@BeforeEach
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void createNewPolicyWhenPolicyTypeFound() {
		try {
			Optional<PolicyType> optionalPolicyType = Mockito.mock(Optional.class);
			PolicyType mockPolicyType=Mockito.mock(PolicyType.class);
			Policy mockPolicy=Mockito.mock(Policy.class);
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyId(2);
			policyDTO.setPolicyName("LIC");
			policyDTO.setTenure(10);
			policyDTO.setMaturityAmount(1100);
			policyDTO.setMonthlyPremium(100);
			policyDTO.setPolicyDescription(anyString());
			policyDTO.setTypeOfPolicy("Life");
			policyDTO.setPolicyType(mockPolicyType);

			when(policyTypeRepository.findByType("Life")).thenReturn(optionalPolicyType);
			when(optionalPolicyType.isPresent()).thenReturn(true);
			when(optionalPolicyType.get()).thenReturn(mockPolicyType);
			when(policyRepository.save(Mockito.any())).thenReturn(mockPolicy);
			String s=policyService.createNewPolicy(policyDTO);
			assertEquals("saved successfully",s);

		}catch (Exception e)
		{
			fail("some error is there");
		}
	}
	@Test
	void createNewPolicyWhenPolicyTypeNotFound() {
		try {
			Optional<PolicyType> optionalPolicyType = Mockito.mock(Optional.class);
			PolicyType mockPolicyType=Mockito.mock(PolicyType.class);
			Policy mockPolicy=Mockito.mock(Policy.class);
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyId(2);
			policyDTO.setPolicyName("LIC");
			policyDTO.setTenure(10);
			policyDTO.setMaturityAmount(1100);
			policyDTO.setMonthlyPremium(100);
			policyDTO.setPolicyDescription(anyString());
			policyDTO.setTypeOfPolicy("Life");
			policyDTO.setPolicyType(mockPolicyType);

			when(policyTypeRepository.findByType("Life")).thenReturn(optionalPolicyType);
			when(optionalPolicyType.isPresent()).thenReturn(false);
			when(optionalPolicyType.get()).thenReturn(mockPolicyType);
			when(policyRepository.save(Mockito.any())).thenReturn(mockPolicy);
			String s=policyService.createNewPolicy(policyDTO);
			assertTrue(false);
		}catch (Exception e)
		{
			assertTrue(true);
		}
	}
	@Test
	void createNewPolicyNegative() {
		try {
			Optional<PolicyType> optionalPolicyType = Mockito.mock(Optional.class);
			PolicyType mockPolicyType=Mockito.mock(PolicyType.class);

			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyId(2);
			policyDTO.setPolicyName("LIC");
			policyDTO.setTenure(10);
			policyDTO.setMaturityAmount(1100);
			policyDTO.setMonthlyPremium(100);
			policyDTO.setPolicyDescription(anyString());
			policyDTO.setTypeOfPolicy("Life");
			policyDTO.setPolicyType(mockPolicyType);

			when(policyTypeRepository.findByType("Life")).thenReturn(optionalPolicyType);
			when(optionalPolicyType.isPresent()).thenReturn(true);
			when(optionalPolicyType.get()).thenReturn(mockPolicyType);
			when(policyRepository.save(Mockito.any())).thenReturn(null);
			String s=policyService.createNewPolicy(policyDTO);
			assertEquals("Failed to save the policy",s);

		}catch (Exception e)
		{
			fail("some error is there");
		}
	}
	@Test
	void createNewPolicyWhenPolicyTypeIsHealthAndTenureLessThanEquals10() {
		try {
			Optional<PolicyType> optionalPolicyType = Mockito.mock(Optional.class);
			PolicyType mockPolicyType=Mockito.mock(PolicyType.class);
			Policy mockPolicy=Mockito.mock(Policy.class);
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyId(2);
			policyDTO.setPolicyName("LIC");
			policyDTO.setTenure(10);
			policyDTO.setMaturityAmount(1080);
			policyDTO.setMonthlyPremium(100);
			policyDTO.setPolicyDescription(anyString());
			policyDTO.setTypeOfPolicy("Health");
			policyDTO.setPolicyType(mockPolicyType);

			when(policyTypeRepository.findByType("Health")).thenReturn(optionalPolicyType);
			when(optionalPolicyType.isPresent()).thenReturn(true);
			when(optionalPolicyType.get()).thenReturn(mockPolicyType);
			when(policyRepository.save(Mockito.any())).thenReturn(mockPolicy);
			String s=policyService.createNewPolicy(policyDTO);
			assertEquals("saved successfully",s);
		}catch (Exception e)
		{
			assertTrue(false);
		}
	}
	@Test
	void createNewPolicyWhenPolicyTypeIsHealthAndTenureMoreThan10() {
		try {
			Optional<PolicyType> optionalPolicyType = Mockito.mock(Optional.class);
			PolicyType mockPolicyType=Mockito.mock(PolicyType.class);
			Policy mockPolicy=Mockito.mock(Policy.class);
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyId(2);
			policyDTO.setPolicyName("LIC");
			policyDTO.setTenure(15);
			policyDTO.setMaturityAmount(1635);
			policyDTO.setMonthlyPremium(100);
			policyDTO.setPolicyDescription(anyString());
			policyDTO.setTypeOfPolicy("Health");
			policyDTO.setPolicyType(mockPolicyType);

			when(policyTypeRepository.findByType("Health")).thenReturn(optionalPolicyType);
			when(optionalPolicyType.isPresent()).thenReturn(true);
			when(optionalPolicyType.get()).thenReturn(mockPolicyType);
			when(policyRepository.save(Mockito.any())).thenReturn(mockPolicy);
			String s=policyService.createNewPolicy(policyDTO);
			assertEquals("saved successfully",s);
		}catch (Exception e)
		{
			assertTrue(false);
		}
	}
	@Test
	void createNewPolicyException() {
		try {
			Optional<PolicyType> optionalPolicyType = Mockito.mock(Optional.class);
			PolicyType mockPolicyType=Mockito.mock(PolicyType.class);
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyId(2);
			policyDTO.setPolicyName("LIC");
			policyDTO.setTenure(9);
			policyDTO.setMaturityAmount(1100);
			policyDTO.setMonthlyPremium(100);
			policyDTO.setPolicyDescription(anyString());
			policyDTO.setTypeOfPolicy("Health");
			policyDTO.setPolicyType(mockPolicyType);

			when(policyTypeRepository.findByType("Health")).thenReturn(optionalPolicyType);
			when(optionalPolicyType.isPresent()).thenReturn(true);
			when(optionalPolicyType.get()).thenReturn(mockPolicyType);
			when(policyRepository.save(Mockito.any())).thenThrow(new RuntimeException());
			String s=policyService.createNewPolicy(policyDTO);
			assertFalse(true);
		}catch (Exception e)
		{
			assertTrue(true);
		}
	}



	//Testing searchPolicyById functions
	@Test
	void searchPolicyByIdPositive()
	{
		try {
			Optional<Policy> mockOptional = Mockito.mock(Optional.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findById(anyInt())).thenReturn(mockOptional);
			when(mockOptional.isPresent()).thenReturn(true);
			when(mockOptional.get()).thenReturn(mockPolicy);
			PolicyDTO policyDTO = policyService.searchPolicyById(anyInt());
			assertNotNull(policyDTO);
		}catch (Exception e)
		{
			assertTrue(false);
		}
	}
	@Test
	void searchPolicyByIdNegative()
	{
		try {
			Optional<Policy> mockOptional = Mockito.mock(Optional.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findById(anyInt())).thenReturn(mockOptional);
			when(mockOptional.isPresent()).thenReturn(false);
			when(mockOptional.get()).thenReturn(mockPolicy);
			PolicyDTO policyDTO = policyService.searchPolicyById(anyInt());
			assertNull(policyDTO);
		}catch (Exception e)
		{
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByIdException()
	{
		try {
			Optional<Policy> mockOptional = Mockito.mock(Optional.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findById(anyInt())).thenThrow(new RuntimeException());
			when(mockOptional.isPresent()).thenReturn(false);
			when(mockOptional.get()).thenReturn(mockPolicy);
			PolicyDTO policyDTO = policyService.searchPolicyById(anyInt());
			assertTrue(false);
		}catch (Exception e)
		{
			assertTrue(true);
		}
	}


	//Testing searchPolicyByTenure
	@Test
	void searchPolicyByTenurePositive()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			Iterator<Policy> mockIterator = Mockito.mock(Iterator.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findAllByTenure(anyInt())).thenReturn(mockIterable);
			when(mockIterable.iterator()).thenReturn(mockIterator);
			when(mockIterator.hasNext()).thenReturn(true).thenReturn(false);
			when(mockIterator.next()).thenReturn(mockPolicy);


			List<PolicyDTO> policyDTOList = policyService.searchPolicyByTenure(anyInt());
			assertFalse(policyDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByTenureNegative()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			Iterator<Policy> mockIterator = Mockito.mock(Iterator.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findAllByTenure(anyInt())).thenReturn(mockIterable);
			when(mockIterable.iterator()).thenReturn(mockIterator);
			when(mockIterator.hasNext()).thenReturn(false);
			when(mockIterator.next()).thenReturn(mockPolicy);
			List<PolicyDTO> policyDTOList = policyService.searchPolicyByTenure(anyInt());
			assertTrue(policyDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByTenureException()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			when(policyRepository.findAllByTenure(anyInt())).thenThrow(new RuntimeException());
			policyService.searchPolicyByTenure(anyInt());
			assertTrue(false);
		}catch (Exception e){
			assertTrue(true);
		}
	}

	//Testing searchPolicyByMaturityAmount
	@Test
	void searchPolicyByMaturityAmountPositive()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			Iterator<Policy> mockIterator = Mockito.mock(Iterator.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findAllByMaturityAmount(anyDouble())).thenReturn(mockIterable);
			when(mockIterable.iterator()).thenReturn(mockIterator);
			when(mockIterator.hasNext()).thenReturn(true).thenReturn(false);
			when(mockIterator.next()).thenReturn(mockPolicy);
			List<PolicyDTO> policyDTOList = policyService.searchPolicyByMaturityAmount(anyDouble());
			assertFalse(policyDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByMaturityAmountNegative()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			Iterator<Policy> mockIterator = Mockito.mock(Iterator.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findAllByMaturityAmount(anyDouble())).thenReturn(mockIterable);
			when(mockIterable.iterator()).thenReturn(mockIterator);
			when(mockIterator.hasNext()).thenReturn(false);
			when(mockIterator.next()).thenReturn(mockPolicy);
			List<PolicyDTO> policyDTOList = policyService.searchPolicyByMaturityAmount(anyDouble());
			assertTrue(policyDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByMaturityAmountException()
	{
		try {
			when(policyRepository.findAllByMaturityAmount(anyDouble())).thenThrow(new RuntimeException());
			policyService.searchPolicyByMaturityAmount(anyDouble());
			assertTrue(false);
		}catch (Exception e){
			assertTrue(true);
		}
	}

	//Testing searchPolicyByPremiumAmount
	@Test
	void searchPolicyByPremiumAmountPositive()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			Iterator<Policy> mockIterator = Mockito.mock(Iterator.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findAllByMonthlyPremium(anyInt())).thenReturn(mockIterable);
			when(mockIterable.iterator()).thenReturn(mockIterator);
			when(mockIterator.hasNext()).thenReturn(true).thenReturn(false);
			when(mockIterator.next()).thenReturn(mockPolicy);


			List<PolicyDTO> policyDTOList = policyService.searchPolicyByPremiumAmount(anyInt());
			assertFalse(policyDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByPremiumAmountNegative()
	{
		try {
			Iterable<Policy> mockIterable = Mockito.mock(Iterable.class);
			Iterator<Policy> mockIterator = Mockito.mock(Iterator.class);
			Policy mockPolicy = Mockito.mock(Policy.class);
			when(policyRepository.findAllByMonthlyPremium(anyInt())).thenReturn(mockIterable);
			when(mockIterable.iterator()).thenReturn(mockIterator);
			when(mockIterator.hasNext()).thenReturn(false);
			when(mockIterator.next()).thenReturn(mockPolicy);
			List<PolicyDTO> policyDTOList = policyService.searchPolicyByPremiumAmount(anyInt());
			assertTrue(policyDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}
	}

	@Test
	void searchPolicyByPremiumAmountException()
	{
		try {
			when(policyRepository.findAllByMonthlyPremium(anyInt())).thenThrow(new RuntimeException());
			policyService.searchPolicyByPremiumAmount(anyInt());
			assertTrue(false);
		}catch (Exception e){
			assertTrue(true);
		}
	}

}
