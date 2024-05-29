package com.capstone.petcare2.DTO;

import lombok.Data;

@Data
public class BillRequest {
	
	private String username;
	private String petName;
	private String servicecharge;
	private String gst;
	private String totalamount;
	private String email;
    private String phoneNumber;
    private String address;
}
