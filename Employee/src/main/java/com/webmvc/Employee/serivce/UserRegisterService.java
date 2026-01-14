package com.webmvc.Employee.serivce;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webmvc.Employee.dtos.UserDTO;
import com.webmvc.Employee.entity.Login;
import com.webmvc.Employee.entity.UserRegister;
import com.webmvc.Employee.repository.LoginRepository;
import com.webmvc.Employee.repository.UserRepository;

@Service
public class UserRegisterService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final LoginRepository loginRepository;
	@Autowired
	public UserRegisterService(UserRepository userRepository,PasswordEncoder passwordEncoder
			,LoginRepository loginRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.loginRepository = loginRepository;
	}
	
	public ResponseEntity<UserRegister>addUser(UserDTO dto){
		
		UserRegister user = new UserRegister();
	
	
		user.setAddress(dto.getAddress());
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPhoneNo(dto.getPhoneNo());
		user.setUsername(dto.getUsername());
		
		if(dto.getEmail().contains("@christiansen.com"))
			user.setRole("ROLE_ADMIN");
		else
			user.setRole("ROLE_USER");
		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(user);
		
		Login login = new Login();
		login.setCreatedAt(LocalDateTime.now());
		login.setEmail(user.getEmail());
		login.setPassword(user.getPassword());
		login.setPhoneNo(user.getPhoneNo());
		login.setReamingAttempt(5);
		login.setRole(user.getRole());
		login.setStatus(true);
		login.setUsername(user.getUsername());
		loginRepository.save(login);
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<String>deleteUser(String id){
		
		userRepository.deleteById(id);
		return ResponseEntity.ok("Employee deleted..!");
	}
	
	public ResponseEntity<List<UserRegister>>getAllUser(){
		return ResponseEntity.ok(userRepository.findAll());
	}
}
