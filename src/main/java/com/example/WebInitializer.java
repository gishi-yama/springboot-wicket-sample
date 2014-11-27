package com.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import lombok.val;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		val filter = context.addFilter("wicket-filter", WicketFilter.class);
		filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
		filter.setInitParameter("applicationBean", "wicketApplication");
		filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
		filter.addMappingForUrlPatterns(null, false, "/*");
		filter.setInitParameter("configuration", "development");
	}

}
