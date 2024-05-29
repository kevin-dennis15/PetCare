package com.capstone.petcare2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.Models.AdminModel;
import com.capstone.petcare2.Repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminModel addAdmin(AdminModel admin) {
        return adminRepository.save(admin);
    }

    public List<AdminModel> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<AdminModel> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }

    public AdminModel updateAdmin(Long id, AdminModel admin) {
        admin.setId(id); // Ensure that the ID matches the path variable
        return adminRepository.save(admin);
    }
}