package com.tikbiz.service;

import java.util.List;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;

/**
 * 
 * @author 387090
 *
 */
public interface TMSService {

	TMSUser login(TMSUser user) throws TikBizException;

	TMSUser register(TMSUser user) throws TikBizException;

	void createTicket(TMSTicket ticket) throws TikBizException;

	TMSTicket findTicketByID(Long ticketId) throws TikBizException;

	TMSTicket editCustomer(TMSTicket ticket) throws TikBizException;

	List<TMSTicket> readAll() throws TikBizException;

	TMSUser dashboardLogin(TMSUser user) throws TikBizException;
	
}
