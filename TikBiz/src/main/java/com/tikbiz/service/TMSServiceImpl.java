package com.tikbiz.service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.repository.TMSTicketRepository;
import com.tikbiz.repository.TMSUserRepository;

@Service
public class TMSServiceImpl implements TMSService {

	public static final Logger logger = LoggerFactory
			.getLogger(TMSServiceImpl.class);

	@Autowired
	private TMSUserRepository tmsUserRepository;

	@Autowired
	private TMSTicketRepository tmsTicketRepository;

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		InetSocketAddress address = new InetSocketAddress(environment.getProperty("tikbiz.proxy"),Integer.parseInt(environment.getProperty("tikbiz.port")));
		Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
		factory.setProxy(proxy);
		return new RestTemplate(factory);
	}

	@Autowired
	private Environment environment;

	@Override
	public TMSUser login(TMSUser user) throws TikBizException {

		TMSUser validatedUser = tmsUserRepository.findByUserNameAndPassword(
				user.getUserName(), user.getPassword());
		if (null == validatedUser) {
			throw new TikBizException("User not found");
		}
		return validatedUser;
	}

	@Override
	public TMSUser dashboardLogin(TMSUser user) throws TikBizException {
		List<String> roles = new ArrayList<String>();
		roles.add("SUPPORT");
		roles.add("SUPPORT-LEAD");
		roles.add("SUPPORT-MANAGER");
		TMSUser validatedUser = tmsUserRepository
				.findByUserNameAndPasswordAndRoleIn(user.getUserName(),
						user.getPassword(), roles);
		if (null == validatedUser) {
			throw new TikBizException("User not found");
		}
		return validatedUser;
	}

	@Override
	public TMSUser register(TMSUser user) throws TikBizException {
		TMSUser registeredUser = null;
		TMSUser existingUser = tmsUserRepository.findByUserName(user
				.getUserName());
		if (null == existingUser) {
			registeredUser = tmsUserRepository.save(user);
			if (null == registeredUser) {
				throw new TikBizException("User registration failed");
			}
		} else {
			throw new TikBizException("User already exists");
		}
		return registeredUser;
	}

	@Override
	public void createTicket(TMSTicket ticket) throws TikBizException {
		tmsTicketRepository.save(ticket);
		List<TMSUser> existingUserList = tmsUserRepository
				.findByRole("SUPPORT");
		if (null == existingUserList) {
			logger.error("There is no User with role as SupportTeam");
		} else {
			String url = null;
			ResponseEntity<String> response = null;
			TMSUser existingUser = tmsUserRepository.findByUserName(ticket
					.getCreatedBy());

			// Send a response message to the user
			String message = MessageFormat.format(environment
					.getRequiredProperty("tikbiz.message.userticket"), ticket
					.getId(), ticket.getPriority());
			url = MessageFormat.format(
					environment.getRequiredProperty("tikbiz.sms.endpoint"),
					existingUser.getMobileNumber(), message);
			try {
				response = restTemplate().getForEntity(url, String.class);
				logger.debug("Formed URL is:" + response.toString());
			} catch (RestClientException exception) {
				logger.error("Formed URL is:" + url);
				logger.error("Exception while sending message"
						+ exception.getMessage());
			}

			for (TMSUser tmsUser : existingUserList) {
				if (tmsUser.getRole().equalsIgnoreCase("SUPPORT")) {
					try {
						// Send a response message to the Support Team
						url = MessageFormat.format(environment
								.getRequiredProperty("tikbiz.sms.endpoint"),
								tmsUser.getMobileNumber(), message);
						response = restTemplate().getForEntity(url,
								String.class);
					} catch (RestClientException exception) {
						logger.error("Formed URL is:" + url);
						logger.error("Exception while sending message"
								+ exception.getMessage());
					}
				}
			}
		}
	}

	@Override
	public TMSTicket findTicketByID(Long ticketId) throws TikBizException {
		TMSTicket ticket = tmsTicketRepository.findOne(ticketId);
		if (ticket == null) {
			throw new TikBizException("Ticket with id " + ticketId
					+ " not found.");
		}
		return ticket;
	}

	@Override
	public TMSTicket editCustomer(TMSTicket ticket) throws TikBizException {
		TMSTicket updatedTicket = null;
		if (tmsTicketRepository.exists(ticket.getId())) {
			updatedTicket = tmsTicketRepository.save(ticket);
		} else {
			throw new TikBizException("Unable to Update. Customer with id "
					+ ticket.getId() + " not found.");
		}
		return updatedTicket;
	}

	@Override
	public List<TMSTicket> readAll() throws TikBizException {
		return tmsTicketRepository.findAll();
	}

}
