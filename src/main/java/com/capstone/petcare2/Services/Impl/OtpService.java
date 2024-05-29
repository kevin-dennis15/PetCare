package  com.capstone.petcare2.Services.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
//import com.medistocks.authentication.DTO.EmailDetails;
//import com.medistocks.authest;
import com.capstone.petcare2.DTO.OtpResponse;
import com.capstone.petcare2.DTO.OtpValidationRequest;
import com.capstone.petcare2.DTO.Response;
import com.capstone.petcare2.Models.Otp;
import com.capstone.petcare2.Repository.OtpRepository;
//import com.medistocks.authentication.Utils.AppUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Builder
public class OtpService {

        private final OtpRepository otpRepository;

        
        public void saveOrUpdateOtp(String email, String otp) {
            Optional<Otp> optionalOtp = otpRepository.findByEmail(email);
            Otp newOtp = optionalOtp.orElse(new Otp());
            newOtp.setEmail(email);
            newOtp.setOtp(otp);
            newOtp.setCreatedAt(LocalDateTime.now());
            otpRepository.save(newOtp);
        }


      

     // Validate OTP
        public boolean validateOtp(String email, String otp) {
            Optional<Otp> otpOptional = otpRepository.findByEmail(email);
            if (otpOptional.isPresent()) {
                Otp otpEntity = otpOptional.get();
                if (otpEntity.getOtp().equals(otp) &&
                    otpEntity.getCreatedAt().plusMinutes(5).isAfter(LocalDateTime.now())) {
                    return true;
                }
            }
            return false;
        }
        //         return Response.builder()
        //                         .statusCode(200)
        //                         .responseMessage("Success")
        //                         .otpResponse(OtpResponse.builder()
        //                                         .isOtpValid(true)
        //                                         .build())
        //                         .build();
        // }

}
