package com.signals.app.api;

/**
 * 
 * @author Sovani
 * A Dummy service to provide traffic and routes related data
 * 
 */
public interface TrafficDetailsService {
	int getAverageNoOfVehiclesOnRoad(String routename, String currtime);
}
