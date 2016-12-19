package com.signals.ui.concurrent;

import com.signals.app.api.SignalManagementService;
import com.signals.ui.model.Junction;
import com.signals.ui.model.Signal;

/**
 * This implements a thread which reads the signal states from the signal management service and updates the signal colors
 * @author Sovani
 *
 */
public class UserInterfaceThread implements Runnable{

	private SignalManagementService signalService;
	private Junction junction;
	
	public UserInterfaceThread(Junction junction, SignalManagementService signalService){
		//this.signal = signal;
		this.junction = junction;
		this.signalService = signalService;
	}
	
	public void run() {
		String[] signalIds = signalService.getSignalsForJunction(junction.getJunctionId());
		while(true){
			try{
				
				for(String signalId:signalIds)
				{
					Signal signal = junction.getSignal(signalId);
					signal.updateState(signalService.getSignalState(signalId));
				}
				Thread.sleep(50);
					
			}catch(Exception e){
				System.out.println("Thread interrupted");
				
			}
		}
		
	}
}

