package com.capstone.petcare2.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.DTO.BillRequest;
import com.capstone.petcare2.Models.Bill;
import com.capstone.petcare2.Models.User;
import com.capstone.petcare2.Repository.BillRepository;
import com.capstone.petcare2.Repository.UserRepository;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> generateBill(Bill bill, String email) {
		BillRequest billrequest = new BillRequest();
		Optional<User> user1 = userRepository.findByEmail(email);
		if (user1.get() == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with Email doesnt exist");
		billRepository.save(bill);
		User user = user1.get();
		billrequest.setUsername(user.getUsername());
		billrequest.setAddress(user.getAddress());
		billrequest.setEmail(user.getEmail());
		billrequest.setPhoneNumber(user.getPhoneNumber());
		LocalDate startDate = bill.getStartDate();
		LocalDate endDate = bill.getEndDate();
		long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);
		double amount = 500 * daysDifference;
		double gst = amount * 0.18;
		double totalamount = amount + gst;
		String formattedAmount = String.format("%.2f", amount);
		String formattedGst = String.format("%.2f", gst);
		String formattedTotalAmount = String.format("%.2f", totalamount);
		billrequest.setPetName(bill.getPetName());
		billrequest.setServicecharge(formattedAmount);
		billrequest.setGst(formattedGst);
		billrequest.setTotalamount(formattedTotalAmount);
		return ResponseEntity.status(HttpStatus.CREATED).body(billrequest);
	}
}
