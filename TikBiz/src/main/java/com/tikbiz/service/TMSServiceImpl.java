package com.tikbiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.repository.TMSTicketRepository;
import com.tikbiz.repository.TMSUserRepository;

@Service
public class TMSServiceImpl implements TMSService{
	
	@Autowired
	private TMSUserRepository tmsUserRepository;
	
	@Autowired
	private TMSTicketRepository tmsTicketRepository;

	@Override
	public TMSUser login(TMSUser user) throws TikBizException{
		TMSUser validatedUser = tmsUserRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if(null == validatedUser){
			throw new TikBizException("User not found");
//			validatedUser = new TMSUser(new Long(123456789),"hameedu","sultan","kaderh","123456");
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
