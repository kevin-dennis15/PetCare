package com.capstone.petcare2.MainController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.petcare2.DTO.BillRequest;
import com.capstone.petcare2.Models.Bill;
import com.capstone.petcare2.Services.BillService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BillGenerationController {

	@Autowired
	private BillService billService;

	@PostMapping("/generate-bill")
	public ResponseEntity<?> generateBill(@RequestBody Bill bill, @RequestHeader("email") String email) {
		ResponseEntity<?> request = billService.generateBill(bill, email);
		return request;
	}
}
