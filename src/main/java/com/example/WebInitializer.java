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

	private static final String PARAM_APP_BEAN = "applicationBean";

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		val filter = context.addFilter("wicket-filter", WicketFilter.class);
		filter.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
		filter.setInitParameter(PARAM_APP_BEAN, "wicketApplication");
		// This line is the only surprise when comparing to the equivalent
		// web.xml. Without some initialization seems to be missing.
		filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
		filter.addMappingForUrlPatterns(null, false, "/*");
		filter.setInitParameter("configuration", "deployment");
	}

}
