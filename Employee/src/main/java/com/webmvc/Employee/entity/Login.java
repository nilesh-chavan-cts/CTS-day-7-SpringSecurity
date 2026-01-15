package com.webmvc.Employee.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

	
	@Id
	private String id;
	private String username;
	private String email;
	private String password;
	private String phoneNo;
	private String role;
	private boolean status;
	private LocalDateTime createdAt;
	private int reamingAttempt;
	private UserRegister user;
}
