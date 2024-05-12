package com.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.dto.SubscriptionDTO;
import com.policy.services.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Subscription",description = "Subscription Rest API")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Operation(summary = "Add a new subscription", description = "This endpoint is used to add a new subscription to a policy.")
    @PostMapping("/policy/{policyId}/subscribe")
    public ResponseEntity<?> addSubscription(@PathVariable int policyId, @Valid @RequestBody SubscriptionDTO subscriptionDTO) throws Exception {
        SubscriptionDTO subscriptionDTO2=null;
        ResponseEntity<?> responseEntity = null;
        subscriptionDTO2 = subscriptionService.addNewSubscription(policyId, subscriptionDTO);

        if (subscriptionDTO2 == null) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity<String>("saved successfully",HttpStatus.OK);
        }
        log.info("Response Entity:"+responseEntity);
        return responseEntity;
    }
}
