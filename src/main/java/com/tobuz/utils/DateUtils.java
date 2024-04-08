package com.tobuz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateUtils {

	public static String getFormalDate(Date dateToConvert) throws ParseException {

		if(dateToConvert==null || "".equals(dateToConvert))
		{
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");		
		String str = sdf.format(dateToConvert);
		return str;

	}
	

}
