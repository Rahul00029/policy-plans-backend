package com.cognizant.controller;

import com.cognizant.dto.PolicyTypeDTO;
import com.cognizant.services.PolicyTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Policy Type", description = "Policy type Rest API")
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class PolicyTypeController {
    @Autowired
    private PolicyTypeService policyTypeService;

    @Operation(summary = "Get all policy types", description = "This endpoint is used to retrieve all policy types.")
    @GetMapping("policy/types")
    public ResponseEntity<?> getAllPolicyTypes() {
        List<PolicyTypeDTO> responseList = policyTypeService.getAllPolicyTypes();
        ResponseEntity<List<PolicyTypeDTO>> responseEntity = null;
        if (responseList.isEmpty()) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity<List<PolicyTypeDTO>>(responseList, HttpStatus.OK);
        }
        log.info("PolicyTypeDTO : "+responseList);
        return responseEntity;
    }
}
