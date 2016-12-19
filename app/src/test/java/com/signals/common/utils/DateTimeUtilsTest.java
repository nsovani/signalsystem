package com.signals.common.utils;

import junit.framework.TestCase;

public class DateTimeUtilsTest extends TestCase{
	public void testDateTimeUtils(){
		String date = DateTimeUtils.getCurrTime();
		System.out.println(date);
		assertTrue(date.contains(":"));
	}

}
