package com.mindtree.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.springsecurity.model.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

	Accounts findByCustomerId(int id);

}
