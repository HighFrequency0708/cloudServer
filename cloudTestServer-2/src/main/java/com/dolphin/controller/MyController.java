package com.dolphin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	

	@RequestMapping(value = "/info/{id}" ,method = RequestMethod.GET)
	public String info(@PathVariable String id,HttpServletRequest request) {
		
		String username = request.getParameter("username");
		
		return "hello I am is compute-server2   and  id:" + id +"  and  username:" + username;
		
	}

}