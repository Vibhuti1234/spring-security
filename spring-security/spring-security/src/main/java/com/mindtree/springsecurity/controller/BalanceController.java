package com.mindtree.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springsecurity.model.AccountTransactions;
import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.repository.AccountTransactionsRepository;

@RestController
public class BalanceController {

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;

	@PostMapping("/balance")
	public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
		List<AccountTransactions> accountTransactions = accountTransactionsRepository
				.findByCustomerIdOrderByTransactionDtDesc(customer.getId());
		if (accountTransactions != null) {
			return accountTransactions;
		} else {
			return null;
		}
	}

}
