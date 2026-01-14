package com.webmvc.Employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.webmvc.Employee.repository")
public class MongoConfig {

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(),"EmployeeRecord");
	}
}
