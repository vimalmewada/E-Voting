package com.evoting.utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormater {
	public static Date stringToDate(String date) {
		
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	    try {
	    	//System.out.println(date);
	        java.util.Date utilDate = format.parse(date);
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	       // System.out.println(sqlDate);
	        return sqlDate;
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		return null;
}
}
