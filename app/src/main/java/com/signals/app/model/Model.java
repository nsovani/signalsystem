package com.signals.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.signals.common.utils.SignalStates;


/**
 * 
 * @author Sovani
 * Data model instance which contains
 * junction to signals map
 * signal to junction map
 * signal to its state map
 * signal to raodname map
 * map of signal to number of lanes for the road it services 
 * and other functionality exposed via SignalManagementService
 */

public class Model {
	private Map<String, List<String>> junctionSignalMap;
	
	private Map<String,String> signalJunctionMap;
	
	private Map<String, Integer> signalStateMap;
	
	private Map<String, String> signalRoadNameMap;
	
	private Map<String,Integer> signalToLaneCount;
	
	public Model(){
		junctionSignalMap = new HashMap<String,List<String>>();
		signalJunctionMap = new HashMap<String,String>();
		signalStateMap = new HashMap<String,Integer>();
		signalRoadNameMap = new HashMap<String,String>();
		signalToLaneCount = new HashMap<String,Integer>();
	}
	
	public void addJunction(String junctionId){
		junctionSignalMap.put(junctionId,new ArrayList<String>());
		
	}
	
	public void addSignalToJunction(String junctionId, String signalId){
		List<String> signals = junctionSignalMap.get(junctionId);
		signals.add(signalId);
		signalJunctionMap.put(signalId,junctionId);
		signalStateMap.put(signalId, SignalStates.RED.getValue());
	}
	
	public void setSignalState(String signalId, int state){
		signalStateMap.put(signalId, new Integer(state));
		String junctionId = signalJunctionMap.get(signalId);
		String[] signals = getSignalsForJunction(junctionId);
		for(String signal:signals){
			if(!signal.equals(signalId)){
				if(SignalStates.GREEN.getValue() == state || SignalStates.YELLOW.getValue() == state){
					signalStateMap.put(signal,SignalStates.RED.getValue());
				}else{
					signalStateMap.put(signal,SignalStates.GREEN.getValue());
				}
			}
		}
	}

	public int getSignalState(String signalId){
		return signalStateMap.get(signalId).intValue();
	}

	public String getJunctionId(String signalId) {
		return signalJunctionMap.get(signalId);
	}

	public String[] getSignalsForJunction(String junctionId) {
		String[] signals = junctionSignalMap.get(junctionId).toArray(new String[0]);
		return signals;
	}

	public void setEmergency(String signalId) {
		setSignalState(signalId,SignalStates.GREEN.getValue() );
	
	}

	public Map<String, String> getSignalRoadNameMap() {
		return signalRoadNameMap;
	}
	
	public Map<String, Integer> getSignalToLaneCount(){
		return signalToLaneCount;
	}
	
}
