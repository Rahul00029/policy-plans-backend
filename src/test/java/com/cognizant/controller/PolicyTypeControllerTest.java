package com.cognizant.controller;

import com.cognizant.dto.PolicyTypeDTO;
import com.cognizant.services.PolicyTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PolicyTypeControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PolicyTypeService policyTypeService;
    @InjectMocks
    private PolicyTypeController policyTypeController;
    @Mock
    private RestTemplate restTemplate;
    @Autowired
    private LocalValidatorFactoryBean validator;
    private MockRestServiceServer mockServer;
    private RestTemplate template;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(policyTypeController).build();
        template = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(template);
    }

    @Test
    void getAllPolicyTypesPositive() {
        try {
            List<PolicyTypeDTO> responseList = new ArrayList<>();
            PolicyTypeDTO policyTypeDTO = Mockito.mock(PolicyTypeDTO.class);
            responseList.add(policyTypeDTO);
            when(policyTypeService.getAllPolicyTypes()).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyTypeController.getAllPolicyTypes();
            List<PolicyTypeDTO> actual = (List<PolicyTypeDTO>) responseEntity.getBody();
            System.out.println(actual);
            assertTrue(actual.size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void getAllPolicyTypesNegative() {
        try {
            List<PolicyTypeDTO> responseList = new ArrayList<>();
            when(policyTypeService.getAllPolicyTypes()).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyTypeController.getAllPolicyTypes();
            assertNull(responseEntity.getBody());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void getAllPolicyTypesStatusCodePositive() {
        try {
            List<PolicyTypeDTO> responseList = new ArrayList<>();
            PolicyTypeDTO policyTypeDTO = Mockito.mock(PolicyTypeDTO.class);
            responseList.add(policyTypeDTO);
            when(policyTypeService.getAllPolicyTypes()).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyTypeController.getAllPolicyTypes();
            assertEquals(200, responseEntity.getStatusCodeValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void getAllPolicyTypesStatusCodeNegative() {
        try {
            List<PolicyTypeDTO> responseList = new ArrayList<>();
            when(policyTypeService.getAllPolicyTypes()).thenReturn(responseList);
            ResponseEntity<?> responseEntity = policyTypeController.getAllPolicyTypes();
            assertEquals(400, responseEntity.getStatusCodeValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void getAllPolicyTypesUriPositive() {
        List<PolicyTypeDTO> responseList = new ArrayList<>();
        PolicyTypeDTO policyTypeDTO = Mockito.mock(PolicyTypeDTO.class);
        responseList.add(policyTypeDTO);
        when(policyTypeService.getAllPolicyTypes()).thenReturn(responseList);
        try {
            MvcResult mvcResult = mockMvc.perform(get("https://localhost:8083/api/policy/types"))
                    .andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void getAllPolicyTypesUriNegative() {
        List<PolicyTypeDTO> responseList = new ArrayList<>();
        PolicyTypeDTO policyTypeDTO = Mockito.mock(PolicyTypeDTO.class);
        responseList.add(policyTypeDTO);
        when(policyTypeService.getAllPolicyTypes()).thenReturn(responseList);
        try {
            MvcResult mvcResult = mockMvc.perform(get("https://localhost:8083/api/policy/type"))
                    .andExpect(status().isNotFound()).andReturn();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}
