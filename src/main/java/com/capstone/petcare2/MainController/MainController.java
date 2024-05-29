package com.capstone.petcare2.MainController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.petcare2.Models.AdminModel;
import com.capstone.petcare2.Models.BookingModel;
import com.capstone.petcare2.Models.PetModel;
import com.capstone.petcare2.Models.SignInRequest;
import com.capstone.petcare2.Models.User;
import com.capstone.petcare2.Repository.UserRepository;
import com.capstone.petcare2.Repository.UserRepository;
import com.capstone.petcare2.Services.AdminService;
import com.capstone.petcare2.Services.BookingService;
import com.capstone.petcare2.Services.PetService;
import com.capstone.petcare2.Services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
    
    
    
    @Autowired
    private PetService petService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private AdminService adminService;

   

   

    // @PostMapping("addUser")
    // public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
    //     UserModel newUser = userService.addUser(user);
    //     return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    // }

    // @PostMapping("/signin")
    // public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
    //     UserModel user = userRepository1.findByEmail(signInRequest.getEmail());

    //     if (user == null) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
    //     }

    //     if (!user.getPassword().equals(signInRequest.getPassword())) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
    //     }

    //     return ResponseEntity.ok().body("Sign in successful");
    // }

    // // @PutMapping("updateUser")
    // // public ResponseEntity<UserModel> updateUser(@RequestParam String email, @RequestBody UserModel newUser) {
    // //     UserModel updatedUser = userService.updateUser(email, newUser);
    // //     return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    // // }

    // @DeleteMapping("deleteUser/{userId}")
    // public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
    //     userService.deleteUser(userId);
    //     return ResponseEntity.noContent().build();
    // }

    // @GetMapping("getUser/{userId}")
    // public ResponseEntity<UserModel> getUserById(@PathVariable Long userId) {
    //     Optional<UserModel> optionalUser = userService.getUserById(userId);
    //     UserModel user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        
    //     return ResponseEntity.ok(user);
    // }

    // @GetMapping("getAllUser")
    // public ResponseEntity<List<UserModel>> getAllUsers() {
    //     List<UserModel> users = userService.getAllUsers();
    //     return ResponseEntity.ok(users);
    // }

//PetController...................................................................................
    

    @GetMapping("getAllPets")
    public ResponseEntity<List<PetModel>> getAllPets() {
        List<PetModel> pets = petService.getAllPets();
        return ResponseEntity.ok().body(pets);
    }

    @GetMapping("getPet/{id}")
    public ResponseEntity<PetModel> getPetById(@PathVariable Long id) {
        Optional<PetModel> pet = petService.getPetById(id);
        return pet.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("addPet")
    public ResponseEntity<PetModel> addPet(@RequestBody PetModel pet) {
        PetModel newPet = petService.addPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPet);
    }

    @PutMapping("updatePet/{id}")
    public ResponseEntity<Void> updatePet(@PathVariable Long id, @RequestBody PetModel updatedPet) {
        petService.updatePet(id, updatedPet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("deletePet/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getPetByOwnerEmail")
    public List<PetModel> getPetByOwnerEmail(@RequestParam String email) {
        return petService.getPetByOwnerEmail(email);
    }

//AdminController----------------------------------------------------------------------------------------------------


    @PostMapping("/addAdmin")
    public ResponseEntity<AdminModel> addAdmin(@RequestBody AdminModel admin) {
        AdminModel savedAdmin = adminService.addAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @GetMapping("getAllAdmin")
    public ResponseEntity<List<AdminModel>> getAllAdmins() {
        List<AdminModel> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("getAdmin/{id}")
    public ResponseEntity<AdminModel> getAdminById(@PathVariable("id") Long id) {
        Optional<AdminModel> admin = adminService.getAdminById(id);
        return admin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("deleteAdmin/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable("id") Long id) {
        adminService.deleteAdminById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateAdmin/{id}")
    public ResponseEntity<AdminModel> updateAdmin(@PathVariable("id") Long id, @RequestBody AdminModel admin) {
        AdminModel updatedAdmin = adminService.updateAdmin(id, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }




//BookingController-------------------------------------------------------------------------------------



@GetMapping("getAllBooking")
public ResponseEntity<List<BookingModel>> getAllBookings() {
    List<BookingModel> bookings = bookingService.getAllBookings();
    return new ResponseEntity<>(bookings, HttpStatus.OK);
}

@GetMapping("getBookingByOwnerEmail")
public List<BookingModel> getBookingByOwnerEmail(@RequestParam String ownerEmail) {
    return bookingService.getBookingByOwnerEmail(ownerEmail);
}



@PostMapping("/createBooking")
public ResponseEntity<BookingModel> createBooking(@RequestBody BookingModel booking) {
    System.out.println("hello");
    BookingModel newBooking = bookingService.createBooking(booking);
    return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
}

@PutMapping("updateBooking/{id}")
public ResponseEntity<BookingModel> updateBooking(@PathVariable Long id, @RequestBody BookingModel booking) {
    BookingModel updatedBooking = bookingService.updateBooking(id, booking);
    return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
}

@DeleteMapping("deleteBooking")
public String deleteBooking(@RequestParam String ownerEmail, @RequestParam String petName) {
    bookingService.deleteBooking(ownerEmail, petName);
    return "Deleted successfully";
}

}


