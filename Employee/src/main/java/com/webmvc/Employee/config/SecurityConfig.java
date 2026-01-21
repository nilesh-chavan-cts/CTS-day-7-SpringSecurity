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

import com.webmvc.Employee.serivce.CustomUserDetailsService;

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

		http.csrf(csrf -> csrf.disable()).cors().and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.authorizeHttpRequests(
						auth -> auth
								.antMatchers("/employee/login").permitAll()
								.antMatchers("/employee/register").permitAll()
								.antMatchers("/employee/mail-test/**").permitAll()
								.antMatchers("/employee/add").hasRole("USER") //
								.antMatchers("/employee/profile/**").hasRole("USER") //
								.antMatchers("/employee/update/**").hasAnyRole("USER","ADMIN")
								.antMatchers("/employee/employee-list").hasRole("ADMIN")
								.antMatchers("/employee/user").hasRole("ADMIN")
								.antMatchers("/department").hasRole("ADMIN")
								.antMatchers("/employee/export/department/**").hasRole("ADMIN")
								/* .antMatchers("/export/department/**").hasRole("ADMIN") */
								// 🔓 ANGULAR STATIC FILES (VERY IMPORTANT)
								.antMatchers("/", "/index.jsp", "/app/**", "/components/**", "/js/**", "/css/**",
										"/**/*.html", "/**/*.js", "/**/*.css")
								.permitAll().anyRequest().authenticated());

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
			UserDetailsService userDetailsService) throws Exception {

		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

		return builder.build();
	}

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}

//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//		UserDetails user = User.withUsername("nilesh").password(passwordEncoder.encode("nilesh")).roles("USER").build();
//		return new InMemoryUserDetailsManager(user);
//	}

//	  @Bean
//	    public ServletContainerInitializer servletContextInitializer() {
//	        return new ServletContainerInitializer() {
//	            public void onStartup(ServletContext servletContext) {
//	                servletContext.setSessionTimeout(1); // minutes
//	            }
//
//				@Override
//				public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
//					// TODO Auto-generated method stub
//					
//				}
//	        };
//	    }
//
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
