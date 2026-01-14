package com.webmvc.Employee.controller;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webmvc.Employee.dtos.EmployeeDTO;
import com.webmvc.Employee.dtos.Login;
import com.webmvc.Employee.entity.Employee;
import com.webmvc.Employee.serivce.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/message")
	public ResponseEntity<String>getMessage(){
		return ResponseEntity.ok("hello message");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody Login login,HttpServletRequest request){
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
        request.getSession(true);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("status", "200");
        response.put("timestamp", String.valueOf(LocalDateTime.now()));
		 return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<Employee>addEmployee(@RequestBody EmployeeDTO dto){
		
		System.out.println(dto);
		//return employeeService.addEmployee(dto);
		return null;
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>>getAllEmployee(){
		return employeeService.getAllEmployee();
	}
}
