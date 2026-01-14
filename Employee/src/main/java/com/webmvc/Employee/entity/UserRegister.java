package com.webmvc.Employee.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userRegister")
public class UserRegister {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String username;
	private String role;
	private String phoneNo;
	private String password;
}
