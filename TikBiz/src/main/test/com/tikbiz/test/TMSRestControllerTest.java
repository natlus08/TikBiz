package com.tikbiz.test;

import static org.junit.Assert.*;

import java.util.Date;

import com.tikbiz.controller.TMSRestController;
import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.model.TMSUser;
import com.tikbiz.repository.TMSUserRepository;
import com.tikbiz.service.TMSService;
import com.tikbiz.service.TMSServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;


public class TMSRestControllerTest {
	
	private TMSRestController tmsRestController;
	
	private TMSService tmsService;
	
	private TMSUserRepository tmsUserRepository;
	
	private TMSUser tmsUser;
	
	private TMSTicket ticket;
	
	@Before
	public void setupTest() throws TikBizException{
		tmsUser = getTMSUser();
		ticket = getTMSTicket();
		this.tmsRestController = new TMSRestController();
		
		this.tmsService = Mockito.mock(TMSServiceImpl.class);
		this.tmsUserRepository = Mockito.mock(TMSUserRepository.class);
		
		ReflectionTestUtils.setField(this.tmsRestController, "tmsService", this.tmsService);
		ReflectionTestUtils.setField(this.tmsService, "tmsUserRepository", this.tmsUserRepository);
		
		Mockito.when(this.tmsUserRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(tmsUser);
		
		Mockito.when(this.tmsService.login(Mockito.any(TMSUser.class))).thenReturn(tmsUser);
		Mockito.when(this.tmsService.register(Mockito.any(TMSUser.class))).thenReturn(tmsUser);
	}

	private TMSUser getTMSUser() {
		TMSUser user=new TMSUser();
		user.setId(Long.valueOf("123"));
		user.setFirstName("John");
		user.setLastName("Cena");
		user.setMobileNumber("9876543210");
		user.setPassword("Qwerty@123");
		user.setRole("SUPPORT");
		user.setUserName("JC123");
		return user;
	}	
	
	private TMSTicket getTMSTicket(){
		TMSTicket ticket = new TMSTicket();
		ticket.setId(Long.valueOf("123"));
		ticket.setCreatedBy("test");
		ticket.setCreatedDate(new Date());
		ticket.setDescription("new test");
		ticket.setModifiedBy("new tester");
		ticket.setModifiedDate(new Date());
		ticket.setPriority("P1");
		ticket.setStatus("NEW");
		return ticket;
	}
	
	@Test
    public void login() throws TikBizException{
        ResponseEntity<TMSUser> responseEntity = tmsRestController.login(tmsUser);
        TMSUser tmsUser1=responseEntity.getBody();
        assertEquals("JC123", tmsUser1.getUserName());
    }
	
	@Test
    public void register() throws TikBizException{
        ResponseEntity<TMSUser> responseEntity = tmsRestController.register(tmsUser);
        TMSUser tmsUser1=responseEntity.getBody();
        assertEquals("JC123", tmsUser1.getUserName());
    }
	
	@Test
    public void create() throws TikBizException{
        ResponseEntity<?> responseEntity = tmsRestController.create(ticket);
        HttpStatus status = responseEntity.getStatusCode();
        assertEquals(HttpStatus.CREATED, status);
    }
	
}
