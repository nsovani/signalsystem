package com.signals.app.concurrent;

import com.signals.common.utils.ExcpetionUtils;
import com.signals.common.utils.ServiceLocator;
import com.signals.common.utils.SignalStates;
/**
 * The thread implements the functionality to update the signal states in data model as per business logic
 * @author Sovani
 *
 */
public class SignalThread implements Runnable{

	private JunctionContext context;
	private String junctionId;
	private boolean isWaiting = false;
	private boolean printed = false;


	public SignalThread(JunctionContext context, String junctionId){
		this.junctionId = junctionId;
		this.context = context;

	}

	public void run() {
		while(true){
			try{
				synchronized(context){
					if( context.isDirty()){
						context.wait();
					}else{
						String[] signals = ServiceLocator.getSignalservice().getSignalsForJunction(junctionId);
						Integer[] durations = new Integer[signals.length];
						int iter = 0;
						for(String signal: signals){
							durations[iter++] = ServiceLocator.getSignalDurationCalculationService().getGreenSignalDuration(signal,context);
						}
						for(int i = 0; i< durations.length; i++){
							if(!printed){
								System.out.println("Signal - " + signals[i] + ":" + durations[i]);
								
							}
							ServiceLocator.getSignalservice().setSignalState(signals[i], SignalStates.GREEN.getValue());
							Thread.sleep((long) ((double)durations[i]*(1.00 - context.getYellowPercentage())));
							ServiceLocator.getSignalservice().setSignalState(signals[i], SignalStates.YELLOW.getValue());
							Thread.sleep((long) ((double)durations[i]*(context.getYellowPercentage())));
						}
						printed = true;
						context.notify();
					}
				}
			}catch(Exception e){
				System.out.println("Thread interrupted");
				ExcpetionUtils.printStackTrace(e);
				if(!isWaiting){
					try{
						Thread.sleep(context.getEmergencyWaitDuration());
						context.wait();

					}catch(Exception e1){

					}
				}
			}
		}

	}


}
