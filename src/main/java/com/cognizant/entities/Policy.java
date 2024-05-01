package com.cognizant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Policies")
public class Policy {
	@Id
	@Column(name="Policy_Id")
	private int policyId;
	
	@Column(name="Policy_Name")
	private String policyName;
	
	@Column(name="Policy_Description")
	private String policyDescription;
	
	@Column(name="Tenure")
	private int tenure;
	
	@Column(name="Monthly_Premium")
	private int monthlyPremium;
	
	@Column(name="Maturity_Amount")
	private double maturityAmount;
	
	@ManyToOne
	@JoinColumn(name="Policy_Type_Id", referencedColumnName="Id")
	@JsonIgnore
	private PolicyType policyTypeId;
}

