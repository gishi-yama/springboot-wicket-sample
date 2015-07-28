package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BazRepository implements IBazRepository {

	@Override
	public String fetchMessage() {
		return "Hello, Spring Boot + Wicket!";
	}

}
