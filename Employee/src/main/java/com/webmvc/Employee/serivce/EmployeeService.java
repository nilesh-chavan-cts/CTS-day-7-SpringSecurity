package com.webmvc.Employee.serivce;


import java.util.List;

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
import com.webmvc.Employee.entity.UserRegister;
import com.webmvc.Employee.repository.EmployeeRepository;
import com.webmvc.Employee.repository.UserRepository;

@Service
public class EmployeeService {

	
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository,UserRepository userRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
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
	
	public Page<Employee>getAllDepratement(String dept,int page,int size){
		
		 Pageable pageable = PageRequest.of(page, size);

	        return employeeRepository.findByDeptIgnoreCase(dept, pageable);
	}
	
	public ResponseEntity<List<Employee>>listOfEmployee(){
		return ResponseEntity.ok(employeeRepository.findAll());
	}
}
