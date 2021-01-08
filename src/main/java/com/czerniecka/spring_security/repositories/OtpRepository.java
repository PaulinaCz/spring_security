package com.czerniecka.spring_security.repositories;

import com.czerniecka.spring_security.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {

    Optional<Otp> findOtpByUsername(String username);
}
