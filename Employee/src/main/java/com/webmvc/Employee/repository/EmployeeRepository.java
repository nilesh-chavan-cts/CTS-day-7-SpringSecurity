package com.webmvc.Employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webmvc.Employee.entity.Employee;
import java.util.List;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{

	Page<Employee> findByEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCase(
            String email,
            String firstName,
            Pageable pageable
    );
	List<Employee> findByDeptIgnoreCase(String dept);

	Employee findByEmail(String email);
}
