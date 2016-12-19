package com.signals.app.api;

/**
 * 
 * @author Sovani
 * Service to read the signal cycle duration, provided for timeslots in a day
 */

public interface ScheduleReaderService {
	int getSignalCycleDuration(String time);
	void loadScheduler();
}
