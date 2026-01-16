package com.webmvc.Employee.serivce;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.webmvc.Employee.entity.OTPVerification;
import com.webmvc.Employee.repository.LoginRepository;
import com.webmvc.Employee.repository.OTPVerificationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OTPVerificationRepository otpVerificationRepository;
    
    private static final int MAX_ATTEMPT=5;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int OTP_LENGTH = 6;
    private static final int OTP_BOUND = (int) Math.pow(10, OTP_LENGTH);
    
    
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
    		
    		log.info("AuthenticationService : Login Success full...!");
    		
    		response.put("message", "Login successful");
    		response.put("email", obj.getEmail());
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
    			
    			log.info("AuthenticationService : Account is Blocked...!");
    			
    		response.put("message", "Account is Blocked");
        		response.put("Reaming Attempts", obj.getReamingAttempt());
        		response.put("timestamp", String.valueOf(LocalDateTime.now()));
        		return ResponseEntity.ok(response);
    		}
    		
    		log.info("AuthenticationService : wrong password...!");
    		response.put("message", "wrong password");
    		response.put("Reaming_Attempts", obj.getReamingAttempt());
    		response.put("timestamp", String.valueOf(LocalDateTime.now()));
    		return ResponseEntity.ok(response);
    	}
    }
    

    public ResponseEntity<String>sendOTP(String email){
    	
    	
    	com.webmvc.Employee.entity.Login login =loginRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("Email not Fount..:("));
    	
    	String OTP = generateOtp();
    	emailService.sendOtpEmail(email, OTP);
    	OTPVerification ver = new OTPVerification();
    	ver.setCreatedAt(LocalDateTime.now().plusMinutes(1));
    	ver.setEmail(email);
    	ver.setOTP(OTP);
    	ver.setLogin(login);
    	otpVerificationRepository.save(ver);
    	return ResponseEntity.ok("OTP Sended...!");
    }
    
    public ResponseEntity<?>verifyOTP(String OTP){
    	
    	OTPVerification verifyOTP =  otpVerificationRepository.findByOTP(OTP);
    	Map<String, Object>response = new HashMap<>();
    	if(verifyOTP==null) {
    		
    		response.put("message", "Wrong OTP");
    		response.put("status",false);
    		return ResponseEntity.ok(response);
    		
    	}
    	
    	if (verifyOTP.getCreatedAt().isBefore(LocalDateTime.now())) {
    		
    		response.put("message", "OTP expired");
    		response.put("status", false);
    	    return ResponseEntity.ok(response);
    	}
    	
    	com.webmvc.Employee.entity.Login login = loginRepository.findByEmail(verifyOTP.getEmail()).orElseThrow(()-> new IllegalArgumentException("Email not found in login database"));
    	login.setReamingAttempt(MAX_ATTEMPT);
    	login.setStatus(true);
    	loginRepository.save(login);
    	
    	response.put("message","OTP Matched..!" );
    	response.put("status", true);
    	response.put("email", verifyOTP.getEmail());

    	
    	return ResponseEntity.ok(response);
    }
    public static String generateOtp() {
        int otp = SECURE_RANDOM.nextInt(OTP_BOUND);
        return String.format("%0" + OTP_LENGTH + "d", otp);
    }


}
