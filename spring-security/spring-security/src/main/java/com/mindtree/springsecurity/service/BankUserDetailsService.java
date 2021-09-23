package com.mindtree.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.model.SecurityCustomer;
import com.mindtree.springsecurity.repository.CustomerRepository;
@Service
public class BankUserDetailsService implements UserDetailsService {
   
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<Customer> customer = customerRepository.findByEmail(username);
		if (customer.size() == 0|| customer.isEmpty()||customer==null) {
			throw new UsernameNotFoundException("User details not found for the user : " + username);
		}
		return new SecurityCustomer(customer.get(0));
	}

}
