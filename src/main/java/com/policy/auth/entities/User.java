package com.policy.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class User {
    @Id
    @Column(name="User_Name")
    String userName;
    @Column(name="Password")
    String password;
    @Column(name="Role")
    String role;
    @Column(name="Is_Account_Locked")
    boolean isAccountLocked;

}
