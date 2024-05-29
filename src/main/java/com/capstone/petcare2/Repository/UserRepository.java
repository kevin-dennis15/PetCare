package com.capstone.petcare2.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.petcare2.Models.User;



public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
}