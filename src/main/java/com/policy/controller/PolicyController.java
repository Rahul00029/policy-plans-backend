package com.policy.controller;

import com.policy.dto.PolicyDTO;
import com.policy.services.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
@Tag(name = "Policy", description = "Policy Rest API")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @Operation(summary = "Add a new policy", description = "This endpoint is used to add a new policy.")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("policy")
    public ResponseEntity<?> addNewPolicy(@Valid @RequestBody PolicyDTO policyDTO) {
        ResponseEntity<?> responseEntity = null;
        String message = policyService.createNewPolicy(policyDTO);
        responseEntity = new ResponseEntity<String>(message, HttpStatusCode.valueOf(200));
        return responseEntity;
    }

    @Operation(summary = "Search policy based on criteria", description = "This endpoint is used to search for policies based on specific criteria.")
    @GetMapping("policy/{searchCriteria}/{value}")
    public ResponseEntity<?> searchPolicyBasedOnCriteria(@PathVariable String searchCriteria, @PathVariable double value) {

        ResponseEntity<?> responseEntity = null;
        switch (searchCriteria.toLowerCase()) {
            case "tenure": {
                List<PolicyDTO> policyDTOList = policyService.searchPolicyByTenure((int)Math.round(value));
                responseEntity = new ResponseEntity<List<PolicyDTO>>(policyDTOList, HttpStatus.OK);
                break;
            }
            case "maturity": {
                List<PolicyDTO> policyDTOList = policyService.searchPolicyByMaturityAmount(value);
                responseEntity = new ResponseEntity<List<PolicyDTO>>(policyDTOList, HttpStatus.OK);
                break;
            }
            case "premium": {
                List<PolicyDTO> policyDTOList = policyService.searchPolicyByPremiumAmount((int)Math.round(value));
                responseEntity = new ResponseEntity<List<PolicyDTO>>(policyDTOList, HttpStatus.OK);
                break;
            }
            default: {
                responseEntity = new ResponseEntity<String>("Search criteria is not correct", HttpStatus.NO_CONTENT);
            }
        }
        log.info("Response Entity:"+responseEntity);
        return responseEntity;
    }

    @Operation(summary = "Get policy by ID", description = "This endpoint is used to retrieve a policy by its ID.")
    @GetMapping("policy/{policyId}")
    public ResponseEntity<?> getPolicyById(@PathVariable int policyId) {
        ResponseEntity<?> responseEntity = null;
        PolicyDTO policyDTO = policyService.searchPolicyById(policyId);
        if(policyDTO==null)
        	responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
        	responseEntity = new ResponseEntity<PolicyDTO>(policyDTO, HttpStatus.OK);
        log.info("Response Entity :"+responseEntity);
        return responseEntity;
    }
}
