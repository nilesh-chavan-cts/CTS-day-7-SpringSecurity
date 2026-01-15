package com.webmvc.Employee.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "otp_verification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPVerification {

	@Id
	private String id;
	private String email;
	private String OTP;
	private LocalDateTime createdAt;
	private Login login;
}
