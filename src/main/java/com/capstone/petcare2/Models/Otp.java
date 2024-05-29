package com.capstone.petcare2.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Otp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Otp;
    private String email;
    private String useCase;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}

