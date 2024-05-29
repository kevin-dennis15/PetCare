package com.capstone.petcare2.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.capstone.petcare2.DTO.ChangePasswordRequest;
import com.capstone.petcare2.DTO.LoginRequest;
import com.capstone.petcare2.DTO.OtpValidationRequest;
import com.capstone.petcare2.DTO.Request;
import com.capstone.petcare2.DTO.Response;
import com.capstone.petcare2.DTO.UserInfo;


@Component
public interface UserService {
    
    ResponseEntity<Response> signUp(Request request);
    ResponseEntity<Response> login(LoginRequest request);
    Response sendOtp();
    Response validateOtp();
    public Response resetPasswordWithOTP(String email, String newPassword);
    Response forgotPassword(String email);
    Response changePassword(ChangePasswordRequest request);
    void updateUser(String userEmail, UserInfo userUpdateRequest);
    UserInfo getUserInfoByEmail(String email);
    void updatePasswordByEmail(String email, String newPassword);
    ResponseEntity<String> verifyOtp(OtpValidationRequest request);
    
}
