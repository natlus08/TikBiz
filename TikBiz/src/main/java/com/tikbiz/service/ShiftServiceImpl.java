package com.tikbiz.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tikbiz.exception.TikBizException;
import com.tikbiz.model.ShiftRoaster;
import com.tikbiz.model.ShiftRoaster.ShiftType;
import com.tikbiz.model.TMSUser;
import com.tikbiz.repository.ShiftRoasterRepository;
import com.tikbiz.repository.TMSUserRepository;

@Service
public class ShiftServiceImpl implements ShiftService{
	
	@Autowired
	private TMSUserRepository tmsUserRepository;

	@Autowired
	private ShiftRoasterRepository shiftRoasterRepository;
	
	static final int MAX_RECORD = 7;
	
	Pageable seven = new PageRequest(0, MAX_RECORD);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Map<String, Map<Date, String>> getShifts(Date date)
			throws TikBizException {
		Map<String, Map<Date, String>> employeeShift = new TreeMap<String, Map<Date, String>>();
			List<ShiftRoaster> shifts = shiftRoasterRepository.findByDateGreaterThanEqual(date, seven);
			List<TMSUser> supportUsers = tmsUserRepository.findByRole("SUPPORT");
			for (TMSUser tmsUser : supportUsers) {
				Map<Date, String> shiftSchedule = new TreeMap<Date, String>();
				for (ShiftRoaster shiftRoaster : shifts) {
					Map<ShiftType, String> shift = shiftRoaster.getShifts();
					for (Map.Entry<ShiftType, String> entry : shift.entrySet()){
					    if(entry.getValue().contains(tmsUser.getUserName())){
					    	shiftSchedule.put(shiftRoaster.getDate(), entry.getKey().toString());
					    }
					}
					
				}
				employeeShift.put(tmsUser.getFirstName(),shiftSchedule);
			}
		return employeeShift;
	}
	
}
