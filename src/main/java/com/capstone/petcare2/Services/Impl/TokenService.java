package  com.capstone.petcare2.Services.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.Models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@SuppressWarnings("deprecation")
@Service
public class TokenService {
    
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    
    public String generateToken(User user) {
        
        long expirationTimeMillis = System.currentTimeMillis() + 3600000; // 1 hour
        Date expirationDate = new Date(expirationTimeMillis);

        
        String token = Jwts.builder()
                .setSubject(user.getEmail()) 
                .setExpiration(expirationDate) 
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }
}
