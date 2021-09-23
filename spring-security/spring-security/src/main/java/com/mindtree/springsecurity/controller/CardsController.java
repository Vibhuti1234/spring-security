package com.mindtree.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springsecurity.model.Cards;
import com.mindtree.springsecurity.model.Customer;
import com.mindtree.springsecurity.repository.CardsRepository;

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@PostMapping("/cards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());
		if (cards != null) {
			return cards;
		} else {
			return null;
		}
	}

}
