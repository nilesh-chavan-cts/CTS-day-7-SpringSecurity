package com.webmvc.Employee.serivce;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.webmvc.Employee.entity.Login;

public class CustomUserDetails implements UserDetails{

	private Login login;
	
	public CustomUserDetails(Login login) {
		super();
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Collections.singletonList(
	            new SimpleGrantedAuthority(login.getRole())
	    );
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
