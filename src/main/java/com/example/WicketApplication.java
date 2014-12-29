package com.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.crypt.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.page.FooPage;

@Component
@EnableAutoConfiguration
@ComponentScan
public class WicketApplication extends WebApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Class<? extends WebPage> getHomePage() {
		return FooPage.class;
	}

	public static void main(String[] args) {
		SpringApplication.run(WicketApplication.class, args);
	}

	@Override
	public void init() {
		super.init();
		getRequestCycleSettings().setResponseRequestEncoding(CharEncoding.UTF_8);
		getMarkupSettings().setDefaultMarkupEncoding(CharEncoding.UTF_8);
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
		mountPage("/FooPage", FooPage.class);
	}
	

}
