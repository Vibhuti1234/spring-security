package com.mindtree.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springsecurity.model.Accounts;
import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.repository.AccountsRepository;

@RestController
public class AccountController {
	@Autowired
	private AccountsRepository accountsRepository;

	@PostMapping("/account")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		Accounts accounts = accountsRepository.findByCustomerId(customer.getId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}
	}

}
