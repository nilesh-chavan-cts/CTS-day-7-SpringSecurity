package com.webmvc.Employee.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@NotBlank(message = "first name can't blank")
	private String firstName;
	@NotBlank(message = "last name can't blank")
	private String lastName;
	@NotBlank(message = "address can't blank")
	private String address;
	@Email
	@NotBlank(message = "email can't blank")
	private String email;
	@NotBlank(message = "username can't blank")
	private String username;
	@NotBlank(message = "phone Number can't blank")
	private String phoneNo;
	@NotBlank(message = "password can't blank")
	private String password;
	private String role;
}
