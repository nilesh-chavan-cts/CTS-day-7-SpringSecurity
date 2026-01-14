package com.webmvc.Employee.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;

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

}
