package com.capstone.petcare2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.petcare2.Models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>
{
	
}


