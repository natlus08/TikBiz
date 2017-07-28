/**
 * 
 */
package com.tikbiz.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.service.ShiftService;
import com.tikbiz.service.TMSService;

@RestController
@RequestMapping("/consoleapi")
public class ConsoleRestController {

	@Autowired
	private TMSService tmsService;
	
	@Autowired
	private ShiftService shiftService;
	
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
	
	/**
	 * GET /getshifts --> Read shift details for a week from the database.
	 */
	@GetMapping("/getshifts/{date}")
	public ResponseEntity<Map<String,Map<Date,String>>> getShifts(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) throws TikBizException {
		Map<String,Map<Date,String>> shifts = shiftService.getShifts(date);
		if (shifts.isEmpty()) {
			return new ResponseEntity<Map<String,Map<Date,String>>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<String,Map<Date,String>>>(shifts, HttpStatus.OK);
	}
	
	@ExceptionHandler(TikBizException.class)
	public ResponseEntity<?> tikBizExceptionHandler(TikBizException exception){
		return new ResponseEntity<TMSUser>(new TMSUser(exception.getMessage()),HttpStatus.CONFLICT);
	}
}
