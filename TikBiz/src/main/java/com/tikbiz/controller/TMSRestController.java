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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.service.TMSService;

@RestController
@RequestMapping("/tmsapi")
public class TMSRestController {

	@Autowired
	private TMSService tmsService;
	
	public static final Logger logger = LoggerFactory.getLogger(TMSRestController.class);
	
	
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
	 * POST /create --> Create a new ticket and save it in the database.
	 * @throws TikBizException 
	 */
	@PostMapping("/createticket")
	public ResponseEntity<?> create(@RequestBody TMSTicket ticket) throws TikBizException {
		tmsService.createTicket(ticket);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * GET /read --> Find a ticket by id from the database.
	 * @throws TikBizException 
	 */
	@GetMapping("/getticket/{id}")
	public ResponseEntity<?> read(@PathVariable("id") Long ticketId) throws TikBizException {
		TMSTicket ticket = tmsService.findTicketByID(ticketId);
		return new ResponseEntity<TMSTicket>(ticket, HttpStatus.OK);
	}
	
	/**
	 * PUT /update --> Update a ticket and save it in the database.
	 * @throws TikBizException 
	 */
	@PutMapping("/updateticket")
	public ResponseEntity<?> update(@RequestBody TMSTicket ticket) throws TikBizException {
		TMSTicket updatedTicket = tmsService.editCustomer(ticket);
		return new ResponseEntity<TMSTicket>(updatedTicket, HttpStatus.OK);
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
