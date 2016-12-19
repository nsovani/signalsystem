package com.signals.app.model;

import junit.framework.TestCase;

public class SchedulerTest extends TestCase{
	
	public void testSchedulerAddition(){
		Scheduler scheduler = new Scheduler();
		scheduler.addToScheduler("10:25", "12:35", "120");
		scheduler.addToScheduler("12:36", "14:35", "60");
		assertTrue(scheduler.getSignalCycleDuration("10:27") == 120);
		assertTrue(scheduler.getSignalCycleDuration("10:19") == -1);
	}
}
