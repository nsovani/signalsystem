package com.signals.app.api.impl;

import com.signals.app.api.SignalManagementService;
import com.signals.common.utils.ServiceLocator;

import junit.framework.TestCase;

public class TrafficDetailsServiceImplTest extends TestCase{
	
	public void testLoadingOfRoadDetails(){
		
		String [] args = new String[4];
		args[0] = "signal.txt";
		args[1] = "context.properties";
		args[2] = "signalmapping.properties";
		args[3] = "signallanemap.properties";
		ServiceLocator.init(args);
		
		SignalManagementService signalService = ServiceLocator.getSignalservice();
		String signalid = signalService.getSignalRoadNameMap().keySet().iterator().next();
		String routename = signalService.getSignalRoadNameMap().get(signalid);
		assertTrue(ServiceLocator.getTrafficdetailsservice().getAverageNoOfVehiclesOnRoad(routename , "10:25") > 0);
	}
}
