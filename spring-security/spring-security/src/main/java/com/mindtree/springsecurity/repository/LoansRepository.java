package com.mindtree.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.springsecurity.model.Loans;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Integer> {

	List<Loans> findByCustomerIdOrderByStartDtDesc(int id);

}
