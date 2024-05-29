package com.capstone.petcare2.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.petcare2.Models.Otp;

public interface OtpRepository extends JpaRepository<Otp,Long> {

    Optional<Otp> findByEmail(String email);
    Otp findByEmailAndUseCase(String email, String useCase);
    
}


