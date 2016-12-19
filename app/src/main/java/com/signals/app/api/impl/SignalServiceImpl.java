package com.signals.app.api.impl;


import java.util.Map;

import com.signals.app.api.SignalManagementService;
import com.signals.app.concurrent.JunctionContext;
import com.signals.app.model.Junction;
import com.signals.app.model.Model;
import com.signals.app.model.ThreadingModel;

/**
 * This is main API of the service management system through which UI and backend are synchronized
 * Provides APIs for the data-model and concurrency model
 * @author Sovani
 *
 */
public class SignalServiceImpl implements SignalManagementService{
	
	private Model model;
	private ThreadingModel threadModel;
	
	public SignalServiceImpl(){
		model = new Model();
		threadModel = new ThreadingModel();
	}

	public void addJunction(String junctionId) {
		model.addJunction(junctionId);
		threadModel.addJunction(junctionId);
	}

	public void addSignalToJunction(String junctionId, String signalId) {
		model.addSignalToJunction(junctionId, signalId);
		threadModel.addSignalToJunction(junctionId, signalId);
		
	}

	public void setSignalState(String signalId, int state) {
		model.setSignalState(signalId, state);
		
	}

	public int getSignalState(String signalId) {
		return model.getSignalState(signalId);
	}

	public String getJunctionId(String signalId) {
		return model.getJunctionId(signalId);
	}

	public String[] getSignalsForJunction(String junctionId) {
		return model.getSignalsForJunction(junctionId);
	}

	public void setEmergency(String signalId) {
		model.setEmergency(signalId);
		Junction junction = threadModel.getJunctionMap().get(model.getJunctionId(signalId));
		JunctionContext context = junction.getContext();
		try{
			junction.getSignalThread().interrupt();
			//junction.getUpdateThread().interrupt();
			synchronized(context){
				context.notify();
			}
		}catch(Exception e){
			
		}
		
	}
	
	public void activateJunction(String junctionId) {
		threadModel.activateJunction(junctionId);
		
	}
	
	public Map<String, String> getSignalRoadNameMap() {
		return model.getSignalRoadNameMap();
	}
	
	public Map<String, Integer> getSignalToLaneCount() {
		return model.getSignalToLaneCount();
	}

}
