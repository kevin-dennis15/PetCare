package com.capstone.petcare2.MainController;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.petcare2.DTO.ChangePasswordRequest;
import com.capstone.petcare2.DTO.ForgotPasswordRequest;
import com.capstone.petcare2.DTO.LoginRequest;
import com.capstone.petcare2.DTO.OtpValidationRequest;
import com.capstone.petcare2.DTO.Request;
import com.capstone.petcare2.DTO.ResetPasswordRequest;
import com.capstone.petcare2.DTO.Response;
import com.capstone.petcare2.DTO.UserInfo;
import com.capstone.petcare2.Models.Otp;
import com.capstone.petcare2.Repository.OtpRepository;
import com.capstone.petcare2.Services.UserService;
import com.capstone.petcare2.Services.Impl.OtpService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")

@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OtpRepository otpRepository;

//	@Autowired
//	private EmailService emailService;

    @PostMapping("signup")
    public ResponseEntity<Response> signup(@RequestBody Request request) {
        return userService.signUp(request);
    }
//	@PostMapping("signup")
//	public ResponseEntity<Response> signup(@RequestBody Request request) {
//		ResponseEntity<Response> signUpResponse = userService.signUp(request);
//		if (signUpResponse.getStatusCode() == HttpStatus.OK) {
//			String email = request.getEmail();
//			String otp = OtpService.generateOtp(); // Generate OTP
//			OtpService.saveOrUpdateOtp(email, otp); // Save OTP
//			EmailDetails emailDetails = new EmailDetails(email, "OTP for Registration", "Your OTP is: " + otp);
//			emailService.sendEmail(emailDetails); // Send OTP via email
//			return ResponseEntity.ok(new Response(200, "Registration successful. OTP sent to email."));
//		}
//		return signUpResponse;
//	}

	@PostMapping("login")
	public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
		return userService.login(request);
	}

	@PostMapping("changePassword")
	public ResponseEntity<Response> changePassword(@RequestBody ChangePasswordRequest request) {
		Response response = userService.changePassword(request);
		HttpStatus status = response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(response);
	}

	@PostMapping("forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody ForgotPasswordRequest request) {
		Response response = userService.forgotPassword(request.getEmail());
		HttpStatus status = response.getStatusCode() == 200 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(response);
	}

	@PutMapping("resetPassword")
	public ResponseEntity<String> updatePasswordByEmail(@RequestBody ResetPasswordRequest request) {
		String email = request.getEmail();
		String newPassword = request.getNewPassword();

		userService.updatePasswordByEmail(email, newPassword);
		return new ResponseEntity<>("Password updated successfully.", HttpStatus.OK);
	}

	@PutMapping("updateUser")
	public void updateUser(@RequestHeader String email, @RequestBody UserInfo userInfo) {
		System.out.println(email);
		userService.updateUser(email, userInfo);
	}

	@GetMapping("getuser")
	public UserInfo getUserInfoByEmail(@RequestParam String email) {
		return userService.getUserInfoByEmail(email);
	}

	@PostMapping("validateOtp")
	public ResponseEntity<String> validateOtp(@RequestBody OtpValidationRequest request) {
		Optional<Otp> otpOptional = otpRepository.findByEmail(request.getEmail());
		System.out.println(request.getEmail());
		if (otpOptional.isPresent()) {
			Otp otp = otpOptional.get();
			if (otp.getOtp().equals(request.getOtp())) {
				if (otp.getCreatedAt().plusMinutes(5).isAfter(LocalDateTime.now())) { // Assuming OTP expires in 5
																						// minutes
					return ResponseEntity.ok("OTP validation successful");
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP has expired");
				}
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No OTP found for the provided email");
		}
	}

}
