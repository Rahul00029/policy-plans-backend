package com.policy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.policy.dto.SubscriptionDTO;
import com.policy.services.SubscriptionService;

@SpringBootTest
class SubscriptionControllerTest {

	private MockMvc mockMvc;
	@Mock
	private SubscriptionService subscriptionService;
	@InjectMocks
	private SubscriptionController subscriptionController;
	@Mock
	private RestTemplate restTemplate;

	@Autowired
	private LocalValidatorFactoryBean validator;
	private MockRestServiceServer mockServer;
	private RestTemplate template;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
		template = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(template);
	}

	@Test
	void addSubscriptionPositive()
	{
		try {
			SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
			subscriptionDTO.setSubscriptionId(1);
			subscriptionDTO.setSubscriptionStatus("New");
			subscriptionDTO.setSubscriptionDate("01-04-2024");
			subscriptionDTO.setRelationToPolicyHolder("Child");
			subscriptionDTO.setUsername("ram123");
			subscriptionDTO.setMedicalCertificateDocURL("abc.docs");
			subscriptionDTO.setPolicyHolderIdProofType("aadhaar");
			subscriptionDTO.setPolicyHolderIdProofNo("22321334");

			when(subscriptionService.addNewSubscription(1,subscriptionDTO)).thenReturn(subscriptionDTO);
			ResponseEntity<?> responseEntity=subscriptionController.addSubscription(1,subscriptionDTO);
			assertNotNull(responseEntity.getBody());
		}catch (Exception e){

			fail("Some Exception");
		}
	}
	
	@Test
	void addSubscriptionNegative()
	{
		try {
			SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
			subscriptionDTO.setSubscriptionId(1);
			subscriptionDTO.setSubscriptionStatus("New");
			subscriptionDTO.setSubscriptionDate("01-04-2024");
			subscriptionDTO.setRelationToPolicyHolder("Child");
			subscriptionDTO.setUsername("ram123");
			subscriptionDTO.setMedicalCertificateDocURL("abc.docs");
			subscriptionDTO.setPolicyHolderIdProofType("aadhaar");
			subscriptionDTO.setPolicyHolderIdProofNo("22321334");

			when(subscriptionService.addNewSubscription(1,subscriptionDTO)).thenReturn(null);
			ResponseEntity<?> responseEntity=subscriptionController.addSubscription(1,subscriptionDTO);
			assertNull(responseEntity.getBody());
		}catch (Exception e){

			fail("Some Exception");
		}
	}
	
	
	 @Test
	    void addSubscriptionStatusCodePositive() {
	        try {
	        	SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
				subscriptionDTO.setSubscriptionId(1);
				subscriptionDTO.setSubscriptionStatus("New");
				subscriptionDTO.setSubscriptionDate("01-04-2024");
				subscriptionDTO.setRelationToPolicyHolder("Child");
				subscriptionDTO.setUsername("ram123");
				subscriptionDTO.setMedicalCertificateDocURL("abc.docs");
				subscriptionDTO.setPolicyHolderIdProofType("aadhaar");
				subscriptionDTO.setPolicyHolderIdProofNo("22321334");

				when(subscriptionService.addNewSubscription(1,subscriptionDTO)).thenReturn(subscriptionDTO);
				ResponseEntity<?> responseEntity=subscriptionController.addSubscription(1,subscriptionDTO);
	            assertEquals(200, responseEntity.getStatusCodeValue());
	        } catch (Exception e) {
	            assertTrue(false);
	        }
	    }

	    @Test
	    void addSubscriptionStatusCodeNegative() {
	        try {
	        	SubscriptionDTO subscriptionDTO=new SubscriptionDTO();
				subscriptionDTO.setSubscriptionId(1);
				subscriptionDTO.setSubscriptionStatus("New");
				subscriptionDTO.setSubscriptionDate("01-04-2024");
				subscriptionDTO.setRelationToPolicyHolder("Child");
				subscriptionDTO.setUsername("ram123");
				subscriptionDTO.setMedicalCertificateDocURL("abc.docs");
				subscriptionDTO.setPolicyHolderIdProofType("aadhaar");
				subscriptionDTO.setPolicyHolderIdProofNo("22321334");

				when(subscriptionService.addNewSubscription(1,subscriptionDTO)).thenReturn(null);
				ResponseEntity<?> responseEntity=subscriptionController.addSubscription(1,subscriptionDTO);
	            assertEquals(400, responseEntity.getStatusCodeValue());
	        } catch (Exception e) {
	            assertTrue(false);
	        }
	    }
	
}
