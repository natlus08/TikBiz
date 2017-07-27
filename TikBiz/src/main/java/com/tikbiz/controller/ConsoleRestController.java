/**
 * 
 */
package com.tikbiz.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.service.TMSService;

@RestController
@RequestMapping("/consoleapi")
public class ConsoleRestController {

	@Autowired
	private TMSService tmsService;
	
	public static final Logger logger = LoggerFactory.getLogger(ConsoleRestController.class);
	
	
	@PostMapping("/login")
	public ResponseEntity<TMSUser> login(@RequestBody TMSUser user) throws TikBizException{
		TMSUser validatedUser = tmsService.login(user);
		return new ResponseEntity<TMSUser>(validatedUser,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<TMSUser> register(@RequestBody TMSUser user) throws TikBizException{
		TMSUser registeredUser = tmsService.register(user);
		return new ResponseEntity<TMSUser>(registeredUser,HttpStatus.OK);
	}
	
	/**
	 * GET /read --> Read all tickets from the database.
	 */
	@GetMapping("/getalltickets")
	public ResponseEntity<List<TMSTicket>> readAll() throws TikBizException {
		List<TMSTicket> tickets = tmsService.readAll();
		if (tickets.isEmpty()) {
			return new ResponseEntity<List<TMSTicket>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TMSTicket>>(tickets, HttpStatus.OK);
	}
	
	@ExceptionHandler(TikBizException.class)
	public ResponseEntity<?> tikBizExceptionHandler(TikBizException exception){
		return new ResponseEntity<TMSUser>(new TMSUser(exception.getMessage()),HttpStatus.CONFLICT);
	}
}
