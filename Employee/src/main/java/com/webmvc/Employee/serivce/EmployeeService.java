package com.webmvc.Employee.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webmvc.Employee.dtos.EmployeeDTO;
import com.webmvc.Employee.entity.Employee;
import com.webmvc.Employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	
	private final EmployeeRepository employeeRepository;
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	public ResponseEntity<Employee>addEmployee(EmployeeDTO dto){
		
		Employee employee = new Employee();
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
		return ResponseEntity.ok(employee);
	}
	
	public ResponseEntity<List<Employee>>getAllEmployee(){
		
		return ResponseEntity.ok(employeeRepository.findAll());
	}
}
