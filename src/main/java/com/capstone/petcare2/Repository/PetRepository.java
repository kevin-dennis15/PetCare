package com.capstone.petcare2.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.petcare2.Models.PetModel;


public interface PetRepository extends JpaRepository<PetModel, Long> {

    PetModel findById(int petId);

    List<PetModel> findByOwnerEmail(String ownerEmail);


}
