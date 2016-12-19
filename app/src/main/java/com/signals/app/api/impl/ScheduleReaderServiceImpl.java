package com.signals.app.api.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.signals.app.api.ScheduleReaderService;
import com.signals.app.model.Scheduler;
/**
 * Service implementation to read the schedule for the timeslots and the signal duration
 * @author Sovani
 *
 */
public class ScheduleReaderServiceImpl implements ScheduleReaderService {
	
	
	private static final Scheduler scheduler = new Scheduler();
	private String SchedulerPath;
	
	public void loadScheduler(){
		FileInputStream fis = null;
		BufferedReader reader;
		try {
			//fis = new FileInputStream(SchedulerPath);
			InputStream is = getClass().getClassLoader().getResourceAsStream(SchedulerPath);
			reader = new BufferedReader(new InputStreamReader(is));
			String line = reader.readLine();
			while(line != null){
				String[] tokens = line.split(" ");
				scheduler.addToScheduler(tokens[0], tokens[1], tokens[2]);
				line = reader.readLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getSignalCycleDuration(String time) {
		return scheduler.getSignalCycleDuration(time);
	}

	public String getSchedulerPath() {
		return SchedulerPath;
	}

	public void setSchedulerPath(String schedulerPath) {
		SchedulerPath = schedulerPath;
	}

	

}
