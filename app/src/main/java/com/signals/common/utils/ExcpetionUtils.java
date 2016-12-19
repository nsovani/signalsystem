package com.signals.common.utils;

public class ExcpetionUtils {
	public static void printStackTrace(Exception e){
		 StackTraceElement[] stackTrace = e.getStackTrace();
		 for(StackTraceElement elem: stackTrace){
			 System.out.println(elem.toString());
		 }
	}
}
