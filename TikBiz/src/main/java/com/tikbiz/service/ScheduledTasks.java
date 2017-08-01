package com.tikbiz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tikbiz.model.ShiftRoaster;
import com.tikbiz.model.TMSUser;
import com.tikbiz.model.ShiftRoaster.ShiftType;
import com.tikbiz.model.TMSTicket;
import com.tikbiz.repository.ShiftRoasterRepository;
import com.tikbiz.repository.TMSTicketRepository;
import com.tikbiz.repository.TMSUserRepository;


@Component
@SpringBootApplication
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	private TMSUserRepository tmsUserRepository;

	@Autowired
	private ShiftRoasterRepository shiftRoasterRepository;
	
	@Autowired
	private TMSTicketRepository tmsTicketRepository;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	
	Pageable seven = new PageRequest(0, 7);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	
	public static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Scheduled(initialDelay = 10000 , fixedRate = 1000)
    public void reportCurrentTime() throws ParseException {
    	
		//Current Date
//		Date currentDate = new Date();
		
		//format current date
//		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		
		//format current time
//		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		
//    	List<ShiftRoaster> shifts = shiftRoasterRepository.findByDateGreaterThanEqual(currentDate, seven);
		//Get a list of support users
//    	List<TMSUser> supportUsers = tmsUserRepository.findByRole("SUPPORT");

		
    	//Messages should go when current time is 4 hours before of shiftTime
//		for (TMSUser tmsUser : supportUsers) {
//			for (ShiftRoaster shiftRoaster : shifts) {
//				
//				//compare current date with DB date
//				if(dateFormatter.format(currentDate).equalsIgnoreCase(dateFormatter.format(shiftRoaster.getDate()))) {
//					
//					Map<ShiftType, String> shift = shiftRoaster.getShifts();
//					for (Map.Entry<ShiftType, String> entry : shift.entrySet()){
//						String shiftTime = null;;
//					    if(entry.getValue().contains(tmsUser.getUserName())){
//					    	if(entry.getKey().equals("MORNING")){
//					    		shiftTime = "6:00:00";
//					    	}else if(entry.getKey().equals("NOON")) {
//					    		shiftTime = "14:00:00";
//					    	} else if(entry.getKey().equals("NIGHT")) {
//					    		shiftTime = "22:00:00";
//					    	}
//					    }
//					  
//					    	//Messages should go when current time is 4 hours before of shiftTime
//					        Date currentDateTime = timeFormatter.parse(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
//					        
//					        float difference = currentDateTime.getTime() - timeFormatter.parse(shiftTime).getTime();
//					    
//					    	if(difference == 4) {
//						    	logger.info("Message should to sent to the following support member :" + tmsUser.getUserName());	
//						    	//Code to send Message
//						    	String message = "Your Shift is about to start in 4 hours from now, Thanks."; 
//						    	String url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=8Dza5VhlaE2KvN7n1l59NA&senderid=TESTIN&channel=2&DCS=0&flashsms=0&number="+tmsUser.getMobileNumber()+"&text="+message+"&route=11";
//								logger.info("Formed URL is:" + url);
//								ResponseEntity<String> response = restTemplate().getForEntity(url,String.class);
//								response.getStatusCode();
//					    	}
//				    	}
//				    }
//			    }
//		    }
//		
//			//Send a Escalation Message when ticket status is not changed in 15 minutes
//			List<TMSTicket> tmsTicketList = tmsTicketRepository.findByCreatedDate(currentDate);
//			
//			for(TMSTicket tmsTicket: tmsTicketList) {
//
//				Date createdDateTime = timeFormatter.parse(new SimpleDateFormat("HH:mm:ss").format(tmsTicket.getCreatedDate()));
//			 
//				float timeDifference = currentDate.getTime() - createdDateTime.getTime();
//				int minutesDifference = (int) ((timeDifference / (1000*60)) % 60);
//				
//				if(tmsTicket.getStatus().equalsIgnoreCase("NEW") ) {
//					
//					if(tmsTicket.getPriority().equalsIgnoreCase("P1") && minutesDifference == 10) {
//						
//						for (TMSUser supportUser : supportUsers) {
//							if(supportUser.getRole().equalsIgnoreCase("SUPPORT-LEAD")) {
//								logger.info("Message should to sent to the following support member :" + supportUser.getUserName());
//								String message = "Ticket raised at level P1 is not yet looked into,please provide necessary support"; 
//						    	String url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=8Dza5VhlaE2KvN7n1l59NA&senderid=TESTIN&channel=2&DCS=0&flashsms=0&number="+supportUser.getMobileNumber()+"&text="+message+"&route=11";
//						    	ResponseEntity<String> response = restTemplate().getForEntity(url,String.class);
//						    	System.out.println("Response code generated " +response.getStatusCode() );
//								logger.info(url, response.getStatusCode());;
//							}
//						}
//						
//					} else if(tmsTicket.getPriority().equalsIgnoreCase("P2") && minutesDifference == 25) {
//						
//						for (TMSUser supportUser : supportUsers) {
//							if(supportUser.getRole().equalsIgnoreCase("SUPPORT-LEAD")) {
//								logger.info("Message should to sent to the following support member :" + supportUser.getUserName());
//								String message = "Ticket raised at level P1 is not yet looked into,please provide necessary support"; 
//						    	String url = "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=8Dza5VhlaE2KvN7n1l59NA&senderid=TESTIN&channel=2&DCS=0&flashsms=0&number="+supportUser.getMobileNumber()+"&text="+message+"&route=11";
//						    	ResponseEntity<String> response = restTemplate().getForEntity(url,String.class);
//						    	System.out.println("Response code generated " +response.getStatusCode() );
//								logger.info(url, response.getStatusCode());;
//							}
//						}
//					}
//				}
//			}
	    	}
		}
		
