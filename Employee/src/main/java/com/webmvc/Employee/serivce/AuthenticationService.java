package com.webmvc.Employee.serivce;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.webmvc.Employee.dtos.Login;
import com.webmvc.Employee.repository.LoginRepository;

@Service
public class AuthenticationService {

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final int MAX_ATTEMPT=5;
    
    public ResponseEntity<?> login(Login login,HttpServletRequest request) {

    	Map<String, Object> response = new HashMap<>();
    	com.webmvc.Employee.entity.Login obj = loginRepository.findByUsername(login.getUsername()).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    	
    	try {
    		
    		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getUsername(),
    				login.getPassword());

    		Authentication authentication = authenticationManager.authenticate(token);


    		SecurityContext context = SecurityContextHolder.createEmptyContext();
    		context.setAuthentication(authentication);

    	
    		request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
    				context);

    		String role = authentication.getAuthorities().iterator().next().getAuthority();
    		
    		obj.setReamingAttempt(MAX_ATTEMPT);
    		obj.setStatus(true);
    		loginRepository.save(obj);
    		
    		response.put("message", "Login successful");
    		response.put("role", role);
    		response.put("timestamp", String.valueOf(LocalDateTime.now()));
    		
    		return ResponseEntity.ok(response);
    	}catch(Exception e) {
    		
    		if(obj.isStatus()==true) {
    			
    			if(obj.getReamingAttempt()>=1) {
    				obj.setReamingAttempt(obj.getReamingAttempt()-1);
    	    		loginRepository.save(obj);
    			}else {
    				
    	    		obj.setStatus(false);
    	    		loginRepository.save(obj);
    			}
    		}else {
    		response.put("message", "Account is Blocked");
        		response.put("Reaming Attempts", obj.getReamingAttempt());
        		response.put("timestamp", String.valueOf(LocalDateTime.now()));
        		return ResponseEntity.ok(response);
    		}
    		response.put("message", "wrong password");
    		response.put("Reaming_Attempts", obj.getReamingAttempt());
    		response.put("timestamp", String.valueOf(LocalDateTime.now()));
    		return ResponseEntity.ok(response);
    	}
    }

}
