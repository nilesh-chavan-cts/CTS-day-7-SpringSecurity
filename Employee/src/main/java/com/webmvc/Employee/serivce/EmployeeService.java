package com.webmvc.Employee.serivce;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webmvc.Employee.dtos.EmployeeDTO;
import com.webmvc.Employee.entity.Employee;
import com.webmvc.Employee.entity.Login;
import com.webmvc.Employee.entity.UserRegister;
import com.webmvc.Employee.repository.EmployeeRepository;
import com.webmvc.Employee.repository.LoginRepository;
import com.webmvc.Employee.repository.UserRepository;

@Service
public class EmployeeService {

	
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final LoginRepository loginRepository;
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository,UserRepository userRepository
			,LoginRepository loginRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
		this.loginRepository = loginRepository;
	}
	
	public ResponseEntity<Employee>addEmployee(EmployeeDTO dto){
		
		Employee employee = new Employee();
		System.out.println(dto);
		UserRegister user=userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalArgumentException("please enter register email"));
		
		employee.setAddress(dto.getAddress());
		employee.setDept(dto.getDept());
		employee.setEmail(dto.getEmail());
		employee.setFirstName(dto.getFirstName());
		employee.setGender(dto.getGender());
		employee.setLastName(dto.getLastName());
		employee.setPhoneNo(dto.getPhoneNo());
		employee.setRole(dto.getRole());
		employee.setSalary(dto.getSalary());
		employee.setUser(user);
		
		employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}
	
	public ResponseEntity<Page<Employee>>getAllEmployee(String keyword,int page,int size){
		
//		Query query = new Query();
//		Pageable pageable =PageRequest.of(page, size);
//		
//	    if (keyword != null && !keyword.isEmpty()) {
//	        query.addCriteria(new Criteria().orOperator(
//	            Criteria.where("email").regex(keyword, "i"),
//	            Criteria.where("firstName").regex(keyword, "i")
//	        ));
//	    }
//
//	    long total = mongoTemplate.count(query, Employee.class);
//
//	    query.with(pageable);
//
//	    List<Employee> list = mongoTemplate.find(query, Employee.class);
//
////	    return new PageImpl<>(list, pageable, total);
		
		  Pageable pageable =PageRequest.of(page, size,Sort.by("firstName"));
		  
		  if (keyword == null || keyword.trim().isEmpty()) {
			  
		  ResponseEntity.ok(employeeRepository.findAll(pageable));  
		  } 
		  return ResponseEntity.ok(employeeRepository.
		 findByEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCase(keyword,
		  keyword, pageable));
		 
	}
	
	public ResponseEntity<Employee>getEmployee(String email){
		return ResponseEntity.ok(employeeRepository.findByEmail(email));
	}
	
	public ResponseEntity<String>deleteEmaployee(String id){
		
		 Employee employee=employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found in findById"));
		UserRegister user = userRepository.findByEmail(employee.getEmail()).orElseThrow(() -> new IllegalArgumentException("User Register email not found"));
		Login login = loginRepository.findByEmail(employee.getEmail()).orElseThrow(() -> new IllegalArgumentException("Login email not found"));
		
		System.out.println(user);
		System.out.println(login);
		userRepository.delete(user);
		loginRepository.delete(login);
		employeeRepository.deleteById(id);
		System.out.println("------------------------------------------------Data delete----------------------------");
		return ResponseEntity.ok("Delete Employee...!");
	}
	
	public ResponseEntity<?>updateEmployee(String email,EmployeeDTO dto){
		
		Map<String, Object>map = new HashMap<>();
		Employee employee = employeeRepository.findByEmail(email);
		if(employee==null) {
			map.put("message", "email not found..!");
			return ResponseEntity.ok(map);
		}
		
		employee.setAddress(dto.getAddress());
		employee.setDept(dto.getDept());
		employee.setEmail(dto.getEmail());
		employee.setFirstName(dto.getFirstName());
		employee.setGender(dto.getGender());
		employee.setLastName(dto.getLastName());
		employee.setPhoneNo(dto.getPhoneNo());
		employee.setRole(dto.getRole());
		employee.setSalary(dto.getSalary());
		
		employeeRepository.save(employee);
		map.put("message", "Employee Updated..!");
		map.put("employee", employee);
		return ResponseEntity.ok(map);
	}
	
	public List<Employee> getEmployeesByDeptForExport(String dept) {
	    return employeeRepository.findByDeptIgnoreCase(dept);
	}

	
	public ResponseEntity<List<Employee>>listOfEmployee(){
		return ResponseEntity.ok(employeeRepository.findAll());
	}
	
	
}
