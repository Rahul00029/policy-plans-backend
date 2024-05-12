package com.policy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.policy.dto.PolicyDTO;
import com.policy.services.PolicyService;

class PolicyControllerTest {
	private MockMvc mockMvc;
	@Mock
	private PolicyService policyService;
	@InjectMocks
	private PolicyController policyController;
	@Mock
	private RestTemplate restTemplate;

	@Mock
	private PolicyDTO mockPolicyDTO;
	
	@Autowired
	private MockRestServiceServer mockServer;
	private RestTemplate template;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(policyController).build();
		template = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(template);
	}

	@Test
	void addNewPolicyPositive()
	{
		try {
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyName("LIC");
			policyDTO.setPolicyId(2);
			policyDTO.setMonthlyPremium(230);
			policyDTO.setPolicyDescription("abc");
			policyDTO.setTypeOfPolicy("Life");
			policyDTO.setTenure(10);

			when(policyService.createNewPolicy(policyDTO)).thenReturn("saved successfully");
			ResponseEntity<?> responseEntity=policyController.addNewPolicy(policyDTO);
			assertEquals("saved successfully",responseEntity.getBody());
		}catch (Exception e){

			fail("Some Exception");
		}
	}
	
	@Test
	void addNewPolicyNegative()
	{
		try {
			PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyName("LIC");
			policyDTO.setPolicyId(2);
			policyDTO.setMonthlyPremium(230);
			policyDTO.setPolicyDescription("abc");
			policyDTO.setTypeOfPolicy("Life");
			policyDTO.setTenure(10);

			when(policyService.createNewPolicy(policyDTO)).thenReturn("Failed to save the policy");
			ResponseEntity<?> responseEntity=policyController.addNewPolicy(policyDTO);
			assertEquals("Failed to save the policy",responseEntity.getBody());
		}catch (Exception e){

			fail("Some Exception");
		}
	}
	
	@Test
    void getPolicyByIdPositive() {
        try {
            PolicyDTO policyDTO=new PolicyDTO();
            policyDTO.setPolicyName("LIC");
            policyDTO.setPolicyId(2);
            policyDTO.setMonthlyPremium(230);
            policyDTO.setPolicyDescription("abc");
            policyDTO.setTypeOfPolicy("Life");
            policyDTO.setTenure(10);
            when(policyService.searchPolicyById(anyInt())).thenReturn(policyDTO);
            ResponseEntity<?> responseEntity = policyController.getPolicyById(2);
            PolicyDTO actual = (PolicyDTO) responseEntity.getBody();
            assertNotNull(actual);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void getPolicyByIdNegative() {
        try {
        	when(policyService.searchPolicyById(anyInt())).thenReturn(null);
            ResponseEntity<?> responseEntity = policyController.getPolicyById(anyInt());
            PolicyDTO actual = (PolicyDTO) responseEntity.getBody();
            assertNull(actual);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    void searchPolicyBasedOnTenurePositive() {
        try {
            List<PolicyDTO> responseList = new ArrayList<>();
            PolicyDTO policyDTO=new PolicyDTO();
			policyDTO.setPolicyName("LIC");
			policyDTO.setPolicyId(2);
			policyDTO.setMonthlyPremium(230);
			policyDTO.setPolicyDescription("abc");
			policyDTO.setTypeOfPolicy("Life");
			policyDTO.setTenure(10);
            responseList.add(policyDTO);
            when(policyService.searchPolicyByTenure(10)).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("tenure",10.0);
            List<PolicyDTO> actual = (List<PolicyDTO>)responseEntity.getBody();
            assertTrue(actual.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void searchPolicyBasedOnTenureNegative() {
        try {
            List<PolicyDTO> responseList = new ArrayList<>();
            when(policyService.searchPolicyByTenure(anyInt())).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("tenure", anyDouble());
            List<PolicyDTO> actual = (List<PolicyDTO>)responseEntity.getBody();
            assertEquals(0,actual.size());

        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    void searchPolicyBasedOnMaturityAmountPositive() {
        try {
            List<PolicyDTO> responseList = new ArrayList<>();
           
            responseList.add(mockPolicyDTO);
            when(policyService.searchPolicyByMaturityAmount(anyDouble())).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("maturity", anyDouble());
            List<PolicyDTO> actual = (List<PolicyDTO>)responseEntity.getBody();
            assertTrue(actual.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void searchPolicyBasedOnMaturityAmountNegative() {
        try {
            List<PolicyDTO> responseList = new ArrayList<>();
            when(policyService.searchPolicyByMaturityAmount(anyDouble())).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("maturity", anyDouble());
            List<PolicyDTO> actual = (List<PolicyDTO>)responseEntity.getBody();
            assertEquals(0,actual.size());

        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    void searchPolicyBasedOnMonthlyPremiumPositive() {
        try {
            List<PolicyDTO> responseList = new ArrayList<>();
           
            responseList.add(mockPolicyDTO);
            when(policyService.searchPolicyByPremiumAmount(anyInt())).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("premium", anyDouble());
            List<PolicyDTO> actual = (List<PolicyDTO>)responseEntity.getBody();
            assertTrue(actual.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void searchPolicyBasedOnMonthlyPremiumNegative() {
        try {
            List<PolicyDTO> responseList = new ArrayList<>();
            when(policyService.searchPolicyByPremiumAmount(anyInt())).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("premium", anyDouble());
            List<PolicyDTO> actual = (List<PolicyDTO>)responseEntity.getBody();
            assertEquals(0,actual.size() );

        } catch (Exception e) {
            assertTrue(false);
        }
    }
    @Test
    void searchPolicyBasedOnWrongCriteria() {
        try {
            ResponseEntity<?> responseEntity = policyController.searchPolicyBasedOnCriteria("wrongCriteria", 10.0);
            String result = (String)responseEntity.getBody();
            assertEquals("Search criteria is not correct",result);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

}
