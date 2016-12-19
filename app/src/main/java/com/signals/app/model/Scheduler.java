package com.signals.app.model;

import java.util.ArrayList;
/**
 * 
 * @author Sovani
 * Scheduler stores in the input schedule and provides the time settings given,
 * for given time as per timeslot duration mapping
 *
 */
public class Scheduler {
	private ArrayList<Integer> startHours;
	private ArrayList<Integer> startMinutes;
	private ArrayList<Integer> endHours;
	private ArrayList<Integer> endMinutes;
	private ArrayList<Integer> signalCycleDuration;
	
	public Scheduler(){
		startHours = new ArrayList<Integer>();
		startMinutes = new ArrayList<Integer>();
		endHours = new ArrayList<Integer>();
		endMinutes = new ArrayList<Integer>();
		signalCycleDuration = new ArrayList<Integer>();
	}
	
	
	/**
	 * 
	 * @param time
	 * @return gives duration as per configuration or returns -1 so that default settings can be used
	 */
	public int getSignalCycleDuration(String time){
		
		String [] tokens = time.split(":");
		Integer startHr = Integer.parseInt(tokens[0]);
		Integer startMin = Integer.parseInt(tokens[1]);
		Integer totalMinutes = startHr * 60 + startMin;
		for(int i = 0 ; i < startHours.size(); i++){
			if (totalMinutes >= ((startHours.get(i) * 60) + startMinutes.get(i)) &&
					(totalMinutes <= (endHours.get(i) * 60) + endMinutes.get(i))){
				return signalCycleDuration.get(i);
			}
		}
		return -1;
	}
	
	public void addToScheduler(String startTime, String endTime, String duration){
		String [] starttokens = startTime.split(":");
		Integer startHr = Integer.parseInt(starttokens[0]);
		Integer startMin = Integer.parseInt(starttokens[1]);
		String [] endtokens = endTime.split(":");
		Integer endHr = Integer.parseInt(endtokens[0]);
		Integer endMin = Integer.parseInt(endtokens[1]);
		Integer durationInt = Integer.parseInt(duration);
		startHours.add(startHr);
		startMinutes.add(startMin);
		endHours.add(endHr);
		endMinutes.add(endMin);
		signalCycleDuration.add(durationInt);
	}
	

}
