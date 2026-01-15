package com.webmvc.Employee.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webmvc.Employee.dtos.EmployeeDTO;
import com.webmvc.Employee.dtos.Login;
import com.webmvc.Employee.dtos.UserDTO;
import com.webmvc.Employee.entity.Employee;
import com.webmvc.Employee.entity.UserRegister;
import com.webmvc.Employee.serivce.AuthenticationService;
import com.webmvc.Employee.serivce.EmailService;
import com.webmvc.Employee.serivce.EmployeeService;
import com.webmvc.Employee.serivce.UserRegisterService;

@RestController
@RequestMapping("/employee")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserRegisterService userRegisterService;
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO dto) {
		
		return userRegisterService.addUser(dto);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody  Login login, HttpServletRequest request) {
		return authenticationService.login(login, request);
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserRegister>>listOfRegisterUser(){
		return userRegisterService.getAllUser();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO dto) {

		 return employeeService.addEmployee(dto);
	}

	@GetMapping("/profile/{email}")
	public ResponseEntity<Employee>getEmloyee(@PathVariable String email){
		return employeeService.getEmployee(email);
	}
	
	@GetMapping("/employee-list")
	public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "0") int size,
			@RequestParam(defaultValue = "8") int page) {
		
		return employeeService.getAllEmployee(keyword,page,size);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String>deleteUser(@PathVariable String id){
		return userRegisterService.deleteUser(id);
	}
	
	@GetMapping("/department/{dept}")
	public ResponseEntity<Page<Employee>> getEmployeesByDept(
	        @PathVariable String dept,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "8") int size) {

	    Page<Employee> employees = employeeService.getAllDepratement(dept, page, size);
	    return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Employee>>getAllListOfEmployee(){
		return employeeService.listOfEmployee();
	}
	
	 @GetMapping("/mail-test/{email}")
	    public ResponseEntity<String> sendEmail(@PathVariable String email) {
	        return authenticationService.sendOTP(email);
	 }
	 @GetMapping("/otp/{OTP}")
	 public ResponseEntity<String> OTPVerifiy(@PathVariable String OTP) {
	        return authenticationService.verifyOTP(OTP);
	 }
	 
}
