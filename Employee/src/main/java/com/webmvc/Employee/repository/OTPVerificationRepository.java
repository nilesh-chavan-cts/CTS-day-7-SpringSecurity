package com.webmvc.Employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webmvc.Employee.entity.OTPVerification;
import java.util.List;


@Repository
public interface OTPVerificationRepository extends MongoRepository<OTPVerification, String>{

	OTPVerification findByOTP(String oTP);
}
