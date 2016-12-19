package com.signals.app.api.impl;

import com.signals.app.api.SignalManagementService;
import com.signals.app.concurrent.JunctionContext;
import com.signals.common.utils.ServiceLocator;

import junit.framework.TestCase;

public class SignalDurationCalculationServiceImplTest extends TestCase{
	public void testSignalDurationCalculation(){
		String [] args = new String[4];
		args[0] = "signal.txt";
		args[1] = "context.properties";
		args[2] = "signalmapping.properties";
		args[3] = "signallanemap.properties";
		
		ServiceLocator.init(args);
		
		SignalManagementService signalService = ServiceLocator.getSignalservice();
		
		signalService.addJunction("testjunction");
		signalService.addSignalToJunction("testjunction", "Road1");
		signalService.addSignalToJunction("testjunction", "Road2");
		signalService.addSignalToJunction("testjunction", "Road3");
		signalService.addSignalToJunction("testjunction", "Road4");
		
		SignalDurationCalculationServiceImpl service = new SignalDurationCalculationServiceImpl();
		JunctionContextReaderServiceImpl contextreader = new JunctionContextReaderServiceImpl();
		contextreader.setContextPropertiesPath("context.properties");
		JunctionContext context = new JunctionContext();
		contextreader.loadContext(context );
		int duration = service.getGreenSignalDuration("Road1", context);
		System.out.println(duration);
		assertTrue(duration >= 10);
		assertTrue(duration <= 1000);
	}
}
