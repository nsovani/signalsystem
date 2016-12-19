package com.signals.app.api;

import java.util.Map;

/**
 * 
 *  @author Sovani
 *  
 *  Service which is interface 
 *  for the UI components, to work with the back-end data model and business logic
 *
 */

public interface SignalManagementService {
	
	public void addJunction(String junctionId);
	public void addSignalToJunction(String junctionId, String signalId);
	public void setSignalState(String signalId, int state);
	public int getSignalState(String signalId);
	public String getJunctionId(String signalId);
	public String[] getSignalsForJunction(String junctionId);
	public void setEmergency(String signalId);
	public void activateJunction(String junctionId);
	public Map<String, String> getSignalRoadNameMap();
	public Map<String, Integer> getSignalToLaneCount();
}
