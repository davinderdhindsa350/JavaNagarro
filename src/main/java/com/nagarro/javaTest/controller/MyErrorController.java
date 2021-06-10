package com.nagarro.javaTest.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController{

	@RequestMapping("/error")
	@ResponseBody
	public String getErrorPath() {
		return "<center><h1>Error in server</h1></center>";
	}
	
	@RequestMapping("/invalidSession")
	@ResponseBody
	public String invalidSession() {
		return "<center><h1>invalid Session</h1></center>";
	}
	@RequestMapping("/")
	@ResponseBody
	public String defaultPath() {
		return "<center><h1>Nagarro JAVA Test</h1></center> ";
	}
}