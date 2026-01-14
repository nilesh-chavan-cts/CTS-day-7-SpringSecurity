package com.webmvc.Employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webmvc.Employee.entity.Login;

@Repository
public interface LoginRepository extends MongoRepository<Login, String>{

	Optional<Login> findByUsername(String username);
	boolean findByEmail(String email);
}
