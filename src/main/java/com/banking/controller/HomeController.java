package com.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank")
public class HomeController {

	@RequestMapping("home")
	public String getHomeView(HttpSession ses) {
		if(ses.getAttribute("name")==null) {
			return "user/user-login";
		}
		return "home/home-page";
	}
	
}
