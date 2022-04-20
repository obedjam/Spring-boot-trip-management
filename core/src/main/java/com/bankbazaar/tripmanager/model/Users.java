package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
public class Users implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name",nullable = false)
    private String userName;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="dob", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name="phone",nullable = false)
    private String phone;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDate createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedTime;

}
