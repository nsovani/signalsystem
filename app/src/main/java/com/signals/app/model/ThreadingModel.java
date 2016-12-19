package com.signals.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.signals.app.concurrent.JunctionContext;
import com.signals.app.concurrent.SignalThread;
import com.signals.app.concurrent.UpdateThread;


/**
 * 
 * @author Sovani
 * MApping of threads to signals and junctions and other management functionality
 */
public class ThreadingModel {
	
	
	private Map<String,Junction> junctionMap;
	private Map<String, List<Signal>> junctionSignalMap;
	
		
	public ThreadingModel(){
		junctionMap = new HashMap<String,Junction>();
		junctionSignalMap = new HashMap<String, List<Signal>>();
		
	}
	
	public void addJunction(String junctionId){
		JunctionContext context = new JunctionContext();
		Junction junction = new Junction(junctionId);
		junction.setContext(context);
		junctionMap.put(junctionId, junction);
		junctionSignalMap.put(junctionId, new ArrayList<Signal>());
	}
	
	public void addSignalToJunction(String junctionId, String signalId){
		List<Signal> signals = junctionSignalMap.get(junctionId);
		signals.add(new Signal(signalId));
	}
	
	public void activateJunction(String junctionId){
		Junction junction = junctionMap.get(junctionId);
		Thread updateThread = new Thread(new UpdateThread(junction.getContext()));
		junction.setUpdateThread(updateThread);
		Thread signalThread = new Thread(new SignalThread(junction.getContext(),junctionId));
		junction.setSignalThread(signalThread);
		junction.activate();
		
	}

	public Map<String, Junction> getJunctionMap() {
		return junctionMap;
	}
}
