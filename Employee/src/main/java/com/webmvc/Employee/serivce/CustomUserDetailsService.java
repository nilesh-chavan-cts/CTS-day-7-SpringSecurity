package com.webmvc.Employee.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webmvc.Employee.entity.Login;
import com.webmvc.Employee.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login =loginRepository.findByUsername(username).orElseThrow(() 
				-> new IllegalArgumentException("Username Not Found in customeUserDetailsService"));

		return new CustomUserDetails(login);
	}

}
