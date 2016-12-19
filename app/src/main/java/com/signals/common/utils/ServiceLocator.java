package com.signals.common.utils;

import com.signals.app.api.JunctionContextReaderService;
import com.signals.app.api.ScheduleReaderService;
import com.signals.app.api.SignalDuartionCalculationService;
import com.signals.app.api.SignalManagementService;
import com.signals.app.api.TrafficDetailsService;
import com.signals.app.api.impl.JunctionContextReaderServiceImpl;
import com.signals.app.api.impl.ScheduleReaderServiceImpl;
import com.signals.app.api.impl.SignalDurationCalculationServiceImpl;
import com.signals.app.api.impl.SignalServiceImpl;
import com.signals.app.api.impl.TrafficDetailsServiceImpl;

/**
 * 
 * @author Sovani
 * Provides the references to all the services to all the functionalities through service apis
 */
public class ServiceLocator {
	private static final SignalManagementService signalService = new SignalServiceImpl();
	private static TrafficDetailsService trafficDetailsService;
	private static final SignalDuartionCalculationService signalDurationCalculationService =  new SignalDurationCalculationServiceImpl();
	private static ScheduleReaderService scheduleReaderService;
	private static JunctionContextReaderService junctionContextReaderService;
	
	public static void init(String args[]){
		
		if(args == null || args.length != 4){
			args = new String[4];
			args[0] = "signal.txt";
			args[1] = "context.properties";
			args[2] = "signalmapping.properties";
			args[3] = "signallanemap.properties";
		}
		ScheduleReaderServiceImpl scheduleReaderServiceImpl = new ScheduleReaderServiceImpl();
    	scheduleReaderServiceImpl.setSchedulerPath(args[0]);
    	scheduleReaderServiceImpl.loadScheduler();
    	scheduleReaderService = scheduleReaderServiceImpl;
    	
    	JunctionContextReaderServiceImpl contextLoaderService = new JunctionContextReaderServiceImpl();
    	contextLoaderService.setContextPropertiesPath(args[1]);
    	junctionContextReaderService = contextLoaderService;
    	
    	TrafficDetailsServiceImpl trafficDetailsServiceImpl = new TrafficDetailsServiceImpl();
    	trafficDetailsServiceImpl.init(args[2], args[3]);
    	trafficDetailsService = trafficDetailsServiceImpl;
    	
    	
	}
	
	public static SignalManagementService getSignalservice() {
		return signalService;
	}

	public static TrafficDetailsService getTrafficdetailsservice() {
		return trafficDetailsService;
	}

	public static SignalDuartionCalculationService getSignalDurationCalculationService() {
		return signalDurationCalculationService;
	}

	public static ScheduleReaderService getScheduleReaderService() {
		return scheduleReaderService;
	}

	public static JunctionContextReaderService getJunctionContextReaderService() {
		return junctionContextReaderService;
	}

	
}
