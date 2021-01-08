package com.czerniecka.spring_security.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// No relationship between classes for simplicity of example
// in our basic example - both entities share username value
// for filtering and matching

// In real life application OTP would be generated, send by sms/email/etc
// and stored in DB for comparison with input

@Entity
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String otp;

    // in the future - add expirationTime for OTP
    // Either: by adding expirationTime field in OTP entity
    // Or: by setting up clearance of entity from DB/local memory after fixed time

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
