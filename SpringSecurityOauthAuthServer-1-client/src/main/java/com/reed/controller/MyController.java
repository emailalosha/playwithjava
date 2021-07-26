package com.reed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView getEmployeeInfo() {
        return new ModelAndView("hello");
    }
	
	@RequestMapping(value = "/code", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView("test");
    }
	
}
