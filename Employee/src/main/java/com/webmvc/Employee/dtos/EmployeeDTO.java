package com.webmvc.Employee.dtos;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	@NotBlank(message = "first name is required")
	private String firstName;
	@NotBlank(message = "last name is required")
	private String lastName;
	@Email
	@NotBlank(message = "email address is required")
	private String email;
	@NotBlank(message = "phone number is required")
	private String phoneNo;
	@NotBlank(message = "Department is required")
	private String dept;
	@NotBlank(message = "address is required")
	private String address;
	@NotNull(message = "salary is required")
	@Min(value = 15000,message = "salary must be greater than 15000")
	private double salary;
	@NotBlank(message = "role is required")
	private String role;
	@NotBlank(message = "gender is required")
	private String gender;

}
