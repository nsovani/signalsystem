package com.signals.app.service.impl;

import com.signals.app.api.impl.ScheduleReaderServiceImpl;

import junit.framework.TestCase;

public class SchedulerReaderServiceTest extends TestCase{
	public void testSchedulerService(){
		ScheduleReaderServiceImpl service = new ScheduleReaderServiceImpl();
		service.setSchedulerPath("signal.txt");
		service.loadScheduler();
		int duration = service.getSignalCycleDuration("10:27");
		System.out.println(duration);
		assertTrue(duration == 120);
		duration = service.getSignalCycleDuration("15:39");
		System.out.println(duration);
		assertTrue(duration == 140);
		duration = service.getSignalCycleDuration("23:59");
		System.out.println(duration);
		assertTrue(duration == -1);
	}
}
