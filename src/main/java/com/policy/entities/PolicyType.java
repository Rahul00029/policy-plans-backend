package com.policy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Policy_Types")
public class PolicyType {
    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "Type")
    private String type;
}
