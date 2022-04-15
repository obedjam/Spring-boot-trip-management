package com.bankbazaar.tripmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="users")
public class Users implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="user_id",nullable = false)
    private Long userId;

    @Column(name="user_name",nullable = false)
    private String userName;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="dob",nullable = false)
    private Date dob;

    @Column(name="phone",nullable = false)
    private String phone;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedTime;

    public void setPassword(String password)
    {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    
    public void updateData(Users user)
    {
        if(user.getUserName()!=null)
        {
            this.setUserName(user.getUserName());
        }
        if(user.getDob()!=null)
        {
            this.setDob(user.getDob());
        }
        if(user.getEmail()!=null)
        {
            this.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null)
        {
            this.setPassword(user.getPassword());
        }
        if(user.getPhone()!=null)
        {
            this.setPhone(user.getPhone());
        }
    }
}
