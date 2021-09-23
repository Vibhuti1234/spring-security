package com.mindtree.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.springsecurity.model.AccountTransactions;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, String>{

	List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int id);

}
