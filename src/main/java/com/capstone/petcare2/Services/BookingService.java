package com.capstone.petcare2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.petcare2.Models.BookingModel;
import com.capstone.petcare2.Models.PetModel;
import com.capstone.petcare2.Repository.BookingRepository;
import com.capstone.petcare2.Repository.PetRepository;

import java.util.List;
import java.util.Optional;


@Service

public class BookingService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private  BookingRepository bookingRepository;

    public List<BookingModel> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public BookingModel getBookingById( Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

   
    public BookingModel createBooking(BookingModel booking) {
        return bookingRepository.save(booking);
    }
   
    public BookingModel updateBooking(@PathVariable Long id,@RequestBody BookingModel updatedBooking) {
        BookingModel existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking != null) {
            updatedBooking.setId(id);
            return bookingRepository.save(updatedBooking);
        }
        return null;
    }
    
  
    public ResponseEntity<String> deleteBooking(String ownerEmail, String petName) {
        try {
            bookingRepository.deleteByOwnerEmailAndPetName(ownerEmail, petName);
            return ResponseEntity.status(HttpStatus.OK).body("Booking deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking");
        }
    }

    public List<BookingModel> getBookingByOwnerEmail(String ownerEmail) {
        return bookingRepository.findByOwnerEmail(ownerEmail);//////////////
    }
}

