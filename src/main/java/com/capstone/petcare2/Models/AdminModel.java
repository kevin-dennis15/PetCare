package com.capstone.petcare2.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Admin")
@Data
public class AdminModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
	public void setId(Long id2) {
		// TODO Auto-generated method stub
		
	}
    
}


/* 
{
    "username": "example_username",
    "password": "example_password",
    "email": "example@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
*/
