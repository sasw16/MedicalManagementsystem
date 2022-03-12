package com.nrifintech.mms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LogoutController {
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView m = new ModelAndView("logout");
		session.removeAttribute("user");
		
		
		return m;
	}
}
