package com.mindtree.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public String registerCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String password=passwordEncoder.encode(customer.getPwd());
		customer.setPwd(password);
		customerRepository.save(customer);
		return "customer registered successfully";
	}

}
