package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BazDao implements IBazDao {

	@Override
	public String fetchMessage() {
		return "Hello, Spring Boot + Wicket!";
	}

}
