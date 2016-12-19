package com.signals.ui.model;


import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.signals.app.api.SignalManagementService;
import com.signals.common.utils.SignalStates;
import com.signals.ui.concurrent.UserInterfaceThread;

/**
 * 
 * @author Sovani
 * Junction class represents 
 */

public class Junction extends JFrame{
	
	private String junctionId;
	
	private Map<String,Signal> signals;
	
	private SignalManagementService signalService;
		
	public Junction(String junctionId,SignalManagementService signalService){
		setSize(800, 800);
		setVisible(true);
		this.signalService = signalService;
		signalService.addJunction(junctionId);
		this.junctionId = junctionId;
		signals = new HashMap<String,Signal>();
	}
	
	public void add(Signal signal){
		super.add(signal);
		signalService.addSignalToJunction(junctionId, signal.getSignalId());
		signalService.setSignalState(signal.getSignalId(), SignalStates.RED.getValue());
		signals.put(signal.getSignalId(),signal);
	}

	public void setSignalService(SignalManagementService signalService) {
		this.signalService = signalService;
	}

	public String getJunctionId() {
		return junctionId;
	}
	
	public void activate(){
		UserInterfaceThread uiThread = new UserInterfaceThread(this, signalService);
		signalService.activateJunction(junctionId);
		Thread userThread = new Thread(uiThread,junctionId);
		userThread.start();
	
	}
	
	public Signal getSignal(String signalId){
		return signals.get(signalId);
	}
	
}
