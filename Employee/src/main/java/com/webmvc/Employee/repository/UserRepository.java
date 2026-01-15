package com.webmvc.Employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webmvc.Employee.entity.UserRegister;

@Repository
public interface UserRepository extends MongoRepository<UserRegister, String>{

	Optional<UserRegister> findByUsername(String username);
	Optional<UserRegister> findByEmail(String email);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
