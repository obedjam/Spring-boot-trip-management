package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    @NotNull(message = "userId cannot be null")
    private Long userId;

    @Column(name="user_name")
    @NotBlank(message = "userName cannot be blank")
    private String userName;

    @Column(name="password")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name="email")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @Column(name="dob")
    @NotNull(message = "Date cannot be null")
    private Date dob;

    @Column(name="phone")
    @NotBlank(message = "PHone cannot be blank")
    private String phone;
}
