package com.mindtree.springsecurity.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindtree.springsecurity.model.Authority;
import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.repository.CustomerRepository;

@Service
public class BankUsernamePwdCustomAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		List<Customer> customer = customerRepository.findByEmail(username);
		if (customer.size() > 0) {
			if (passwordEncoder.matches(pwd, customer.get(0).getPwd())) {
				/*List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username, pwd, authorities);*/
				return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(customer.get(0).getAuthorities()));
			} else {
				throw new BadCredentialsException("Invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details!");
		}	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		// TODO Auto-generated method stub
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}
