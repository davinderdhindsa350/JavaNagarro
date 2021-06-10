package com.nagarro.javaTest;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration(value = "applicationProperties")
public class ApplicationProperties {
	
	@Autowired
	private ServletContext servletContext;

	@Value("${virtual.host.name}")
	public String localHostName;
	
	@Autowired
	private Environment env;

	public String getProperty(String key) {
		return getProperty(key, "");
	}
	
	public String getProperty(String key, String defaultValue) {
		return env.getProperty(key, defaultValue);
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
	
}
