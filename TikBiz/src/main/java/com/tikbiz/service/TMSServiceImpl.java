package com.tikbiz.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.repository.TMSTicketRepository;
import com.tikbiz.repository.TMSUserRepository;

@Service
public class TMSServiceImpl implements TMSService{
	
	public static final Logger logger = LoggerFactory.getLogger(TMSServiceImpl.class);
	
	@Autowired
	private TMSUserRepository tmsUserRepository;
	
	@Autowired
	private TMSTicketRepository tmsTicketRepository;
	
	@Autowired
	private ShiftService shiftService;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Override
	public TMSUser login(TMSUser user) throws TikBizException{
		
		TMSUser validatedUser = tmsUserRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if(null == validatedUser){
			throw new TikBizException("User not found");
		}
		return validatedUser;
	}
	
	@Override
	public TMSUser dashboardLogin(TMSUser user) throws TikBizException{
		List<String> roles = new ArrayList<String>();
		roles.add("SUPPORT");
		roles.add("SUPPORT-LEAD");
		roles.add("SUPPORT-MANAGER");
		TMSUser validatedUser = tmsUserRepository.findByUserNameAndPasswordAndRoleIn(user.getUserName(), user.getPassword(), roles);
		if(null == validatedUser){
			throw new TikBizException("User not found");
		}
		return validatedUser;
	}

	@Override
	public TMSUser register(TMSUser user) throws TikBizException {
		TMSUser registeredUser = null;
		TMSUser existingUser = tmsUserRepository.findByUserName(user.getUserName());
		if(null == existingUser){
			registeredUser = tmsUserRepository.save(user);
			if(null == registeredUser){
				throw new TikBizException("User registration failed");
			}
		}else{
			throw new TikBizException("User already exists");
		}
		return registeredUser;
	}

	@Override
	public void createTicket(TMSTicket ticket) throws TikBizException {
		tmsTicketRepository.save(ticket);
		@SuppressWarnings("unchecked")
		List<TMSUser> existingUserList = tmsUserRepository.findByRole("SUPPORT");
		if(null == existingUserList){
			logger.error("There is no User with role as SupportTeam");
		} else {
			String url = null;
			ResponseEntity<String> response = null;
			TMSUser existingUser = tmsUserRepository.findByUserName(ticket.getCreatedBy());
			
			//Send a response message to the user
			String message = "Ticket :"+ticket.getErrorMessage()+"of priority "+ticket.getPriority()+"has been generated";	
			url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=8Dza5VhlaE2KvN7n1l59NA&senderid=TESTIN&channel=2&DCS=0&flashsms=0&number="+existingUser.getMobileNumber()+"&text="+message+"&route=11";
			logger.info("Formed URL is:" + url);
			response = restTemplate().getForEntity(url,String.class);
			
			for(TMSUser tmsUser : existingUserList) {
				if(tmsUser.getRole().equalsIgnoreCase("SUPPORT")) {
					try {
						//Send a response message to the Support Team
						url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=8Dza5VhlaE2KvN7n1l59NA&senderid=TESTIN&channel=2&DCS=0&flashsms=0&number="+tmsUser.getMobileNumber()+"&text="+message+"&route=11";
						logger.info("Formed URL is:" + url);
						response = restTemplate().getForEntity(url,String.class);
						
					} catch(Exception e) {
						logger.error("There is no User with role as SupportTeam");
					}
				}
			}
		}
	}

	@Override
	public TMSTicket findTicketByID(Long ticketId) throws TikBizException {
		TMSTicket ticket = tmsTicketRepository.findOne(ticketId);
		if (ticket == null) {
			throw new TikBizException("Ticket with id " + ticketId + " not found.");
		}
		return ticket;
	}

	@Override
	public TMSTicket editCustomer(TMSTicket ticket) throws TikBizException {
		if(tmsTicketRepository.exists(ticket.getId())){
			ticket = tmsTicketRepository.save(ticket);
		}else{
			throw new TikBizException("Unable to Update. Customer with id " + ticket.getId() + " not found.");
		}
		return ticket;
	}

	@Override
	public List<TMSTicket> readAll() throws TikBizException {
		return tmsTicketRepository.findAll();
	}
	
}

