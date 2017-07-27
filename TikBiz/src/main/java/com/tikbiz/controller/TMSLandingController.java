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
@RequestMapping("/tms")
public class TMSLandingController {
	
	@GetMapping
	String getIndexPage() {
		return "tms/tmshome";
	}
	
	@GetMapping("/partial/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return "tms/"+page;
	}
}
