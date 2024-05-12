package com.policy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @Id
    @Column(name = "Subscription_Id")
    private int subscriptionId;

    @Column(name = "Policy_Id")
    private int policyId;

    @Column(name = "Subscription_Date")
    private String subscriptionDate;

    @Column(name = "Policy_Holder_Name")
    private String policyHolderName;

    @Column(name = "Username")
    private String username;

    @Column(name = "Subscription_Status")
    private String subscriptionStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Policies_Policy_Id", referencedColumnName = "Policy_Id")
    @JsonIgnore
    private Policy policiesPolicyId;

    @Column(name = "Medical_Certificate_Doc_URL")
    private String medicalCertificateDocURL;

    @Column(name = "Relation_To_Policy_Holder")
    private String relationToPolicyHolder;

    @Column(name = "Policy_Holder_Id_Proof_Type")
    private String policyHolderIdProofType;
    @Column(name = "Policy_Holder_Id_Proof_No")
    private String policyHolderIdProofNo;

}
