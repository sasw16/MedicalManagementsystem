package com.nrifintech.mms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping(value = "errors", method = RequestMethod.GET)
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest, Model m) {

		ModelAndView errorPage = new ModelAndView("errorPage");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);
		System.out.println("Error Code: " + httpErrorCode);

		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Unauthorized";
			break;
		}
		case 404: {
			errorMsg = "Resource not found";
			break;
		}
		case 500: {
			errorMsg = "Internal Server Error";
			break;
		}
		}
		m.addAttribute("errorMsg", errorMsg);
		m.addAttribute("errorCode", httpErrorCode);
		return errorPage;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
