package com.webmvc.Employee.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import com.webmvc.Employee.dtos.EmployeeDTO;
import com.webmvc.Employee.dtos.Login;
import com.webmvc.Employee.dtos.UserDTO;
import com.webmvc.Employee.entity.Employee;
import com.webmvc.Employee.entity.UserRegister;
import com.webmvc.Employee.serivce.AuthenticationService;
import com.webmvc.Employee.serivce.EmailService;
import com.webmvc.Employee.serivce.EmployeeService;
import com.webmvc.Employee.serivce.UserRegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
//@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserRegisterService userRegisterService;
	
	
	
	//@GetMapping(value ="/api/list-employee", produces = "text/plain")
	@GetMapping(value = "/api/list-employee", produces = "application/json")
	public ResponseEntity<List<Employee>> getAllEmployeeInfo() {
	    return employeeService.listOfEmployee();
	}

	
	
	@GetMapping(value ="/api/hello", produces = "text/plain")
    public String sayHello() {
        return "Hello World from Spring REST!";
    }
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Login request) {

    	System.out.println(request+"Request");
        if ("admin".equals(request.getUsername()) &&
            "admin123".equals(request.getPassword())) {

            return ResponseEntity.ok("LOGIN_SUCCESS");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("INVALID_CREDENTIALS");
    }
    
    
    
    
    
    
    
	
	
	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO dto) {	
		log.info("Controller : addUser() method is called..!");
		return userRegisterService.addUser(dto);
	}

	@PostMapping("/employee/login")
	public ResponseEntity<?> login(@RequestBody  Login login, HttpServletRequest request) {
		log.info("Controller : login() method is called..!");
		return authenticationService.login(login, request);
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserRegister>>listOfRegisterUser(){
		log.info("Controller : listOfRegisterUser() method is called..!");
		return userRegisterService.getAllUser();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO dto) {
		log.info("Controller : addEmployee() method is called..!");
		 return employeeService.addEmployee(dto);
	}

	@GetMapping("/profile/{email}")
	public ResponseEntity<Employee>getEmloyee(@PathVariable String email){
		
		return employeeService.getEmployee(email);
	}
	
	@GetMapping("/employee-list")
	public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "0") int size,
			@RequestParam(defaultValue = "8") int page) {
		log.info("Controller : getAllEmployee() method is called..!");
		return employeeService.getAllEmployee(keyword,page,size);
	}
	
	@PutMapping("/update/{email}")
	public ResponseEntity<?>updateEmployee(@PathVariable String email,@RequestBody EmployeeDTO dto){
		log.info("Controller : updateEmployee() method is called..!");
		return employeeService.updateEmployee(email, dto);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteEmployee(@PathVariable String id){
		log.info("Controller : deleteEmployee() method is called..!");
		return employeeService.deleteEmaployee(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String>deleteUser(@PathVariable String id){
		log.info("Controller : deleteUser() method is called..!");
		return userRegisterService.deleteUser(id);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Employee>>getAllListOfEmployee(){
		log.info("Controller : getAllListOfEmployee() method is called..!");
		return employeeService.listOfEmployee();
	}
	
	 @GetMapping("/mail-test/{email}")
	    public ResponseEntity<String> sendEmail(@PathVariable String email) {
		 
		 log.info("Controller : sendEmail() method is called..!");
	     return authenticationService.sendOTP(email);
		
	 }
	 @GetMapping("/otp/{OTP}")
	 public ResponseEntity<?> OTPVerifiy(@PathVariable String OTP) {
		 log.info("Controller : OTPVerifiy() method is called..!");
	      return authenticationService.verifyOTP(OTP);
	 }
	 
	 @GetMapping(value = "/export/department/{dept}", produces = "text/csv")
	 public void exportEmployeesByDept(
	         @PathVariable String dept,
	         HttpServletResponse response) throws IOException {

	     response.setContentType("text/csv");
	     response.setHeader(
	         "Content-Disposition",
	         "attachment; filename=employees_" + dept + ".csv"
	     );

	     log.info("Controller : exportEmployeesByDept() method is called..!");
	     List<Employee> employees =
	         employeeService.getEmployeesByDeptForExport(dept);

	     PrintWriter writer = response.getWriter();

	     // CSV HEADER
	     writer.println("First Name,Last Name,Email,Phone,Department,Role,Salary,Gender,Address");

	     // CSV DATA
	     for (Employee e : employees) {
	         writer.println(
	             e.getFirstName() + "," +
	             e.getLastName() + "," +
	             e.getEmail() + "," +
	             e.getPhoneNo() + "," +
	             e.getDept() + "," +
	             e.getRole() + "," +
	             e.getSalary() + "," +
	             e.getGender() + "," +
	             e.getAddress()
	         );
	     }

	     writer.flush();
	     writer.close();
	 }

	 
}
