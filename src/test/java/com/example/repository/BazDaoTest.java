package com.example.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BazDaoTest {

	@Test
	public void getMessageでDAOの検索結果を返す() {
		IBazDao service = new BazDao();
		assertThat(service.fetchMessage(), is("Hello, Spring Boot + Wicket!"));
	}

}
