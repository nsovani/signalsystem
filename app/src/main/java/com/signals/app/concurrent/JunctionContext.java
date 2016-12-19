package com.signals.app.concurrent;


/**
 * Represents the junction configuration.
 * It is main object on which, the threads synchronize.
 * @author Sovani
 *
 */
public class JunctionContext {
	
	public static final int DEFAULT_SIGNAL_CYCLE_TIME = 240;
	
	private int nextTimeSlot = 0;
	private double yellowPercentage;
	private int totalSignalTime;
	private int minSignalDuration;
	private int maxSignalDuration;
	private boolean isDirty;
	private int emergencyWaitDuration;
	private int updateInterval;
	
	public void getDefaultTime(){
		totalSignalTime = DEFAULT_SIGNAL_CYCLE_TIME;
	}
	
	public JunctionContext(){
		isDirty = false;
	}
	
	
	public double getYellowPercentage() {
		return yellowPercentage;
	}

	public int getNextTimeSlot() {
		return nextTimeSlot;
	}

	public void setNextTimeSlot(int nextTimeSlot) {
		this.nextTimeSlot = nextTimeSlot;
	}


	public void setYellowPercentage(double yellowPercentage) {
		this.yellowPercentage = yellowPercentage;
	}


	public int getTotalSignalTime() {
		return totalSignalTime;
	}


	public void setTotalSignalTime(int totalSignalTime) {
		this.totalSignalTime = totalSignalTime;
	}


	public int getMinSignalDuration() {
		return minSignalDuration;
	}


	public void setMinSignalDuration(int minSignalDuration) {
		this.minSignalDuration = minSignalDuration;
	}


	public int getMaxSignalDuration() {
		return maxSignalDuration;
	}


	public void setMaxSignalDuration(int maxSignalDuration) {
		this.maxSignalDuration = maxSignalDuration;
	}


	public boolean isDirty() {
		return isDirty;
	}


	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public int getEmergencyWaitDuration() {
		return emergencyWaitDuration;
	}

	public void setEmergencyWaitDuration(int emergencyWaitDuration) {
		this.emergencyWaitDuration = emergencyWaitDuration;
	}

	public int getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}


	
}
