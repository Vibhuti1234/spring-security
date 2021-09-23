package com.mindtree.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.springsecurity.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>{

}
