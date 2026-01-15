package com.webmvc.Employee.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private String id;

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private String dept;
	private String address;
	private double salary;
	private String role;
	private String gender;
	private UserRegister user;
}
