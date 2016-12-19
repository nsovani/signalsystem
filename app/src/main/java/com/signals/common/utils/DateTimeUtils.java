package com.signals.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	public static String getCurrTime(){
		Date date = new Date();
		return dateFormat.format(date);
	}
}
