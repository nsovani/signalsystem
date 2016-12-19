package com.signals.app.api.impl;

import com.signals.app.concurrent.JunctionContext;

import junit.framework.TestCase;

public class JunctionContextReaderServiceImplTest extends TestCase {
	public void testContextPopulation(){
		JunctionContextReaderServiceImpl contextreader = new JunctionContextReaderServiceImpl();
		contextreader.setContextPropertiesPath("context.properties");
		JunctionContext context = new JunctionContext();
		contextreader.loadContext(context );
		
		assertTrue(context.getEmergencyWaitDuration() == 3000);
		assertTrue(context.getMinSignalDuration() == 10);
		assertTrue(context.getMaxSignalDuration() == 1000);
		assertTrue(context.getYellowPercentage() == 0.50);
		assertTrue(context.getUpdateInterval() == 30000);
	}
}
