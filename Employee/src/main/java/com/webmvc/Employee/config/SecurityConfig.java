package com.webmvc.Employee.config;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
//		http
//		.csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests()
//		.antMatchers("/login").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().loginPage("/login")
//		.loginProcessingUrl("/login")
//		.defaultSuccessUrl("/home",true)
//		.permitAll();
//		
//		return http.build();
//	}
//	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication()
//		.withUser("nilesh")
//		.password(passwordEncoder().encode("nilesh"))
//		.roles("USER");
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and().authorizeRequests().antMatchers("/employee/login").permitAll().anyRequest().authenticated()
				.and()
		        .httpBasic();

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

		UserDetails user = User.withUsername("nilesh").password(passwordEncoder.encode("nilesh")).roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}

	  @Bean
	    public ServletContainerInitializer servletContextInitializer() {
	        return new ServletContainerInitializer() {
	            public void onStartup(ServletContext servletContext) {
	                servletContext.setSessionTimeout(1); // minutes
	            }

				@Override
				public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
					// TODO Auto-generated method stub
					
				}
	        };
	    }

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

}
