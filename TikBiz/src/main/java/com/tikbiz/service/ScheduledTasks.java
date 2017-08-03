package com.tikbiz.service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.MessageFormat;
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
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
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
		 SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		 InetSocketAddress address = new InetSocketAddress(environment.getProperty("tikbiz.proxy"),Integer.parseInt(environment.getProperty("tikbiz.port")));
		 Proxy proxy = new Proxy(Proxy.Type.HTTP,address);
		 factory.setProxy(proxy);
		 return new RestTemplate(factory);
	}

	@Autowired
	private Environment environment;

	final int MAX_RECORD = 7;

	final int SHIFT_START_REMINDER_DIFFERENCE = 4;

	final int ESCALATION_DIFFERENCE_TEN = 10;

	final int ESCALATION_DIFFERENCE_TWENTY_FIVE = 25;

	final int ESCALATION_DIFFERENCE_FOURTY = 40;

	final int ESCALATION_DIFFERENCE_FIFTY_FIVE = 55;

	final int THOUSAND = 1000;

	final int SIXTY = 60;

	Pageable seven = new PageRequest(0, MAX_RECORD);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// format current date
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

	// format current time
	SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

	public static final Logger logger = LoggerFactory
			.getLogger(ScheduledTasks.class);

	@Scheduled(initialDelay = 10000, fixedRate = 600000)
	public void reportCurrentTime() throws ParseException {

		// Current Date
		Date currentDate = new Date();
		List<ShiftRoaster> shifts = shiftRoasterRepository
				.findByDateGreaterThanEqual(currentDate, seven);
		// Get a list of support users
		List<TMSUser> supportUsers = tmsUserRepository.findByRole("SUPPORT");
		// Messages should go when current time is 4 hours before of shiftTime
		for (TMSUser tmsUser : supportUsers) {
			for (ShiftRoaster shiftRoaster : shifts) {
				// compare current date with DB date
				if (dateFormatter.format(currentDate).equalsIgnoreCase(
						dateFormatter.format(shiftRoaster.getDate()))) {
					Map<ShiftType, String> shift = shiftRoaster.getShifts();
					for (Map.Entry<ShiftType, String> entry : shift.entrySet()) {
						String shiftTime = null;
						if (entry.getValue().contains(tmsUser.getUserName())) {
							if (entry.getKey().equals(
									ShiftRoaster.ShiftType.MORNING)) {
								shiftTime = "6:00:00";
							} else if (entry.getKey().equals(
									ShiftRoaster.ShiftType.NOON)) {
								shiftTime = "14:00:00";
							} else if (entry.getKey().equals(
									ShiftRoaster.ShiftType.NIGHT)) {
								shiftTime = "22:00:00";
							}
						}

						if (null != shiftTime) {
							// Messages should go when current time is 4 hours
							// before of shiftTime
							Date currentDateTime = timeFormatter
									.parse(new SimpleDateFormat("HH:mm:ss")
											.format(Calendar.getInstance()
													.getTime()));

							float difference = currentDateTime.getTime()
									- timeFormatter.parse(shiftTime).getTime();

							if (difference == SHIFT_START_REMINDER_DIFFERENCE) {
								logger.info("Message should be sent to the following support member :"
										+ tmsUser.getUserName());
								// Code to send Message
								String url = MessageFormat
										.format(environment
												.getRequiredProperty("tikbiz.sms.endpoint"),
												tmsUser.getMobileNumber(),
												environment
														.getRequiredProperty("tikbiz.message.shiftstart"));
								try {
									ResponseEntity<String> response = restTemplate()
											.getForEntity(url, String.class);
									logger.debug(response.toString());
								} catch (RestClientException exception) {
									logger.info("Formed URL is:" + url);
									logger.error("Exception while sending message"
											+ exception.getMessage());
								}
							}
						}
					}
				}
			}
		}

		// Send a Escalation Message when ticket status is not changed in 15
		// minutes
		escalationFlow(currentDate, supportUsers);
	}

	/**
	 * Send a Escalation Message when ticket status is not changed in 15 minutes
	 * 
	 * @param currentDate
	 * @param supportUsers
	 * @throws ParseException
	 */
	private void escalationFlow(Date currentDate, List<TMSUser> supportUsers)
			throws ParseException {
		List<TMSTicket> tmsTicketList = tmsTicketRepository.findByStatus("NEW");
		for (TMSTicket tmsTicket : tmsTicketList) {

			Date createdDateTime = timeFormatter.parse(new SimpleDateFormat(
					"HH:mm:ss").format(tmsTicket.getCreatedDate()));

			float timeDifference = currentDate.getTime()
					- createdDateTime.getTime();
			int minutesDifference = (int) ((timeDifference / (THOUSAND * SIXTY)) % SIXTY);

			if (tmsTicket.getPriority().equalsIgnoreCase("P1")
					&& minutesDifference == ESCALATION_DIFFERENCE_TEN) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(),
						tmsTicket.getId());
			} else if (tmsTicket.getPriority().equalsIgnoreCase("P2")
					&& minutesDifference == ESCALATION_DIFFERENCE_TWENTY_FIVE) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(),
						tmsTicket.getId());
			} else if (tmsTicket.getPriority().equalsIgnoreCase("P3")
					&& minutesDifference == ESCALATION_DIFFERENCE_FOURTY) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(),
						tmsTicket.getId());
			} else if (tmsTicket.getPriority().equalsIgnoreCase("P4")
					&& minutesDifference == ESCALATION_DIFFERENCE_FIFTY_FIVE) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(),
						tmsTicket.getId());
			}
		}
	}

	/**
	 * send message to support-lead roles
	 * 
	 * @param supportUsers
	 * @param ticketPriority
	 * @param ticketId
	 */
	public void sendMessageToSupportLead(List<TMSUser> supportUsers,
			String ticketPriority, Long ticketId) {

		for (TMSUser supportUser : supportUsers) {
			if (supportUser.getRole().equalsIgnoreCase("SUPPORT-LEAD")) {
				logger.info("Message should be sent to the following support member :"
						+ supportUser.getUserName());
				String message = MessageFormat.format(environment
						.getRequiredProperty("tikbiz.message.escalation"),
						ticketId.toString(), ticketPriority);
				String url = MessageFormat.format(
						environment.getRequiredProperty("tikbiz.sms.endpoint"),
						supportUser.getMobileNumber(), message);
				try {
					ResponseEntity<String> response = restTemplate()
							.getForEntity(url, String.class);
					logger.info(url, response.getStatusCode());
				} catch (RestClientException exception) {
					logger.error("Exception while sending message"
							+ exception.getMessage());
				}

			}
		}
	}
}
