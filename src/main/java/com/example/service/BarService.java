package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.IBazDao;

@Service
public class BarService implements IBarService {
	
	@Autowired
	private IBazDao dao;

	@Override
	public String fetchMessage() {
//		return "Hello, Spring Boot + Wicket!";
		return dao.fetchMessage();
	}

}
