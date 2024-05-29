package com.capstone.petcare2.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.petcare2.Models.PetModel;
import com.capstone.petcare2.Repository.PetRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<PetModel> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<PetModel> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public List<PetModel> getPetByOwnerEmail(String email) {
        return petRepository.findByOwnerEmail(email);
    }

    

    public PetModel addPet(PetModel pet) {
        return petRepository.save(pet);
    }

    public void updatePet(Long id, PetModel updatedPet) {
        Optional<PetModel> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            PetModel existingPet = optionalPet.get();
        if (updatedPet.getBreed() != null) {
            existingPet.setBreed(updatedPet.getBreed());
        }
        // Update species if not null
        if (updatedPet.getSpecies() != null) {
            existingPet.setSpecies(updatedPet.getSpecies());
        }
        // Update age if not null
        if (updatedPet.getAge() != 0) {
            existingPet.setAge(updatedPet.getAge());
        }
        // Update notes if not null
        if (updatedPet.getNotes() != null) {
            existingPet.setNotes(updatedPet.getNotes());
        }

        // Make sure to preserve the existing owner
        existingPet.setOwnerEmail(existingPet.getOwnerEmail());
            // Save the updated pet
            petRepository.save(existingPet);
        }
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
