package com.cognizant.dto;


import com.cognizant.validation.ValidateIdProofNo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import java.time.LocalDate;


@Data
public class SubscriptionDTO {
	@Range(min=1,message="{com.cognizant.dto.SubscriptionDTO.subscriptionId.error}")
	private int subscriptionId;
	private int policyId;
	@NotNull
	private String subscriptionDate=(LocalDate.now()).toString();
	@Size(min = 5, message="{com.cognizant.dto.SubscriptionDTO.PolicyHolderName.error}")
	private String policyHolderName;
	@NotNull
	private String username;
	@Pattern(regexp = "^(?i)(New|Matured|Defaulted|Terminated)$",message="{com.cognizant.dto.SubscriptionDTO.subscriptionStatus.error}")
	private String subscriptionStatus;
	private String medicalCertificateDocURL;
	@Pattern(regexp = "^(?i)(Parent|Child|Sibling|Spouse|Cousin)$",message="{com.cognizant.dto.SubscriptionDTO.relationToPolicyHolder.error}")
	private String relationToPolicyHolder;
	@NotNull
	private String policyHolderIdProofType;
	@NotNull
	@ValidateIdProofNo
	private String policyHolderIdProofNo;

}
