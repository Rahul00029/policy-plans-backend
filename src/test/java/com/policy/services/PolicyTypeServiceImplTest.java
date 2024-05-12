package com.policy.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.policy.dto.PolicyTypeDTO;
import com.policy.entities.PolicyType;
import com.policy.repositories.PolicyTypeRepository;
import com.policy.services.impl.PolicyTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.List;
class PolicyTypeServiceImplTest {
	@Mock
	private PolicyTypeRepository policyTypeRepository;
	@InjectMocks
	private PolicyTypeServiceImpl policyTypeService;
	@BeforeEach
	void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void getAllPolicyTypePositive() {
		try {
			Iterable<PolicyType> iterableMock=Mockito.mock(Iterable.class);
			PolicyType policyTypeMock=Mockito.mock(PolicyType.class);

			when(policyTypeRepository.findAll()).thenReturn(iterableMock);
			Iterator<PolicyType>iteratorMock=Mockito.mock(Iterator.class);
			when(iterableMock.iterator()).thenReturn(iteratorMock);
			when(iteratorMock.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
			when(iteratorMock.next()).thenReturn(policyTypeMock);
			List<PolicyTypeDTO> policyTypeDTOList=policyTypeService.getAllPolicyTypes();
			assertFalse(policyTypeDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}

	}

	@Test
	void getAllPolicyTypeNegative() {
		try {
			Iterable<PolicyType> iterableMock=Mockito.mock(Iterable.class);
			PolicyType policyTypeMock=Mockito.mock(PolicyType.class);

			when(policyTypeRepository.findAll()).thenReturn(iterableMock);
			Iterator<PolicyType>iteratorMock=Mockito.mock(Iterator.class);
			when(iterableMock.iterator()).thenReturn(iteratorMock);
			when(iteratorMock.hasNext()).thenReturn(false);
			when(iteratorMock.next()).thenReturn(policyTypeMock);

			List<PolicyTypeDTO> policyTypeDTOList=policyTypeService.getAllPolicyTypes();
			assertTrue(policyTypeDTOList.isEmpty());
		}catch (Exception e){
			assertTrue(false);
		}

	}
	@Test
	void getAllPolicyTypeException() {
		try {
			Iterable<PolicyType> iterableMock=Mockito.mock(Iterable.class);
			PolicyType policyTypeMock=Mockito.mock(PolicyType.class);

			when(policyTypeRepository.findAll()).thenThrow(new RuntimeException());
			List<PolicyTypeDTO> policyTypeDTOList=policyTypeService.getAllPolicyTypes();
			assertTrue(false);
		}catch (Exception e){
			assertTrue(true);
		}

	}

}
