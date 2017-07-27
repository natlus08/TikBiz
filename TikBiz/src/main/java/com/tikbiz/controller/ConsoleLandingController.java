package com.tikbiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 387090
 *
 */
@Controller
@RequestMapping("/console")
public class ConsoleLandingController {
	
	@GetMapping
	String getIndexPage() {
		return "console/consolehome";
	}
	
	@GetMapping("/partial/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return "console/"+page;
	}
}
