package com.mindtree.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.model.Loans;
import com.mindtree.springsecurity.repository.LoansRepository;

@RestController
public class LoansController {
	@Autowired
	private LoansRepository loansRepository;
	
	@PostMapping("/loans")
	public List<Loans> getLoanDetails(@RequestBody Customer customer) {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
		if (loans != null ) {
			return loans;
		}else {
			return null;
		}
	}

}
