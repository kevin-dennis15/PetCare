package com.capstone.petcare2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.petcare2.Models.BookingModel;

import jakarta.transaction.Transactional;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {

    List<BookingModel> findByOwnerEmail(String ownerEmail);

    @Transactional
    void deleteByOwnerEmailAndPetName(@Param("ownerEmail") String ownerEmail, @Param("petName") String petName);
    
}

