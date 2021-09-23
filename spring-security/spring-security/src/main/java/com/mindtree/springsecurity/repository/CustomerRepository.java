package com.mindtree.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.springsecurity.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	 
	// Customer findByEmail(String email);
	List<Customer> findByEmail(String email);

}
