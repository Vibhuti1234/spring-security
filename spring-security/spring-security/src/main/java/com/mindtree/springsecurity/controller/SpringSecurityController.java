package com.mindtree.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.service.CustomerService;

@RestController
@RequestMapping("/api")
public class SpringSecurityController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/unsecure", method = RequestMethod.GET)
	public ResponseEntity<String> unsecureApi() {
		String response = " This api is unsecured";
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {

		String response = customerService.registerCustomer(customer);

		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

}
