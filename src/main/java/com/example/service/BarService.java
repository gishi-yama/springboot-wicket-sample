package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.IBazRepository;

@Service
public class BarService implements IBarService {

  @Autowired
  private IBazRepository dao;

  @Override
  public String fetchMessage() {
    // return "Hello, Spring Boot + Wicket!";
    return dao.fetchMessage();
  }

}
