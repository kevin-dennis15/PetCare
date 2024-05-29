package com.capstone.petcare2.Models;

import java.sql.Date;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "bookings")
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "owner_email")
    private String ownerEmail;

    @Column(name = "pet_name")
    private String petName;
    private int pet_id;
    private LocalDate startTime;
    private LocalDate endTime;
    private String status;

}





