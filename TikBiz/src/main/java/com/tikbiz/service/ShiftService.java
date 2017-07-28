package com.tikbiz.service;

import java.util.Date;
import java.util.Map;

import com.tikbiz.exception.TikBizException;

/**
 * 
 * @author 387090
 *
 */
public interface ShiftService {

	Map<String, Map<Date, String>> getShifts(Date date) throws TikBizException;
	
}
