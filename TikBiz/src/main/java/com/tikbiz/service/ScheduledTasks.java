package com.tikbiz.service;

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

	@Autowired
	private Environment environment;

	Pageable seven = new PageRequest(0, 7);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static final Logger logger = LoggerFactory
			.getLogger(ScheduledTasks.class);

	@Scheduled(initialDelay = 10000, fixedRate = 600000)
	public void reportCurrentTime() throws ParseException {

		// Current Date
		Date currentDate = new Date();

		// format current date
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

		// format current time
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

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

							if (difference == 4) {
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
								} catch (Exception exception) {
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
		List<TMSTicket> tmsTicketList = tmsTicketRepository.findByStatus("NEW");

		for (TMSTicket tmsTicket : tmsTicketList) {

			Date createdDateTime = timeFormatter.parse(new SimpleDateFormat(
					"HH:mm:ss").format(tmsTicket.getCreatedDate()));

			float timeDifference = currentDate.getTime()
					- createdDateTime.getTime();
			int minutesDifference = (int) ((timeDifference / (1000 * 60)) % 60);

			if (tmsTicket.getPriority().equalsIgnoreCase("P1")
					&& minutesDifference == 10) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(), tmsTicket.getId());
			} else if (tmsTicket.getPriority().equalsIgnoreCase("P2")
					&& minutesDifference == 25) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(), tmsTicket.getId());
			} else if (tmsTicket.getPriority().equalsIgnoreCase("P3")
					&& minutesDifference == 40) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(), tmsTicket.getId());
			} else if (tmsTicket.getPriority().equalsIgnoreCase("P4")
					&& minutesDifference == 55) {
				sendMessageToSupportLead(supportUsers, tmsTicket.getPriority(), tmsTicket.getId());
			}
		}
	}

	public void sendMessageToSupportLead(List<TMSUser> supportUsers,
			String ticketPriority, Long ticketId) {

		for (TMSUser supportUser : supportUsers) {
			if (supportUser.getRole().equalsIgnoreCase("SUPPORT-LEAD")) {
				logger.info("Message should be sent to the following support member :"
						+ supportUser.getUserName());
				String message = MessageFormat
						.format(environment
								.getRequiredProperty("tikbiz.message.escalation"),ticketId.toString(),
								ticketPriority);
				String url = MessageFormat
						.format(environment
								.getRequiredProperty("tikbiz.sms.endpoint"),
								supportUser.getMobileNumber(),
								message);
				try {
					ResponseEntity<String> response = restTemplate()
							.getForEntity(url, String.class);
					logger.info(url, response.getStatusCode());
					;
				} catch (Exception exception) {
					logger.error("Exception while sending message"
							+ exception.getMessage());
				}

			}
		}
	}
}
