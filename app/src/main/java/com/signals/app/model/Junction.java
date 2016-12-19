package com.signals.app.model;

import java.util.ArrayList;
import java.util.List;

import com.signals.app.concurrent.JunctionContext;
/**
 * 
 * @author Sovani
 * This class represents a junction in a back end
 * 
 */
public class Junction{
	
	private String junctionId;
	
	private List<Signal> signals;
	
	private Thread updateThread;
	
	private Thread signalThread;
	
	private JunctionContext context;
	
	public Junction(String junctionId){
		this.junctionId = junctionId;
		signals = new ArrayList<Signal>();
	}
	
	/**
	 * Add signal to the junction
	 * @param signal
	 */
	public void add(Signal signal){
		signals.add(signal);
		signal.setJunction(this);
	}

	public JunctionContext getContext() {
		return context;
	}

	public void setContext(JunctionContext context) {
		this.context = context;
	}
	
	
	/**
	 *  Starts the context update thread and signal processing threads for the junction
	 */
	public void activate(){
		updateThread.start();
		signalThread.start();
	}

	public Thread getUpdateThread() {
		return updateThread;
	}

	public void setUpdateThread(Thread updateThread) {
		this.updateThread = updateThread;
	}

	public Thread getSignalThread() {
		return signalThread;
	}

	public void setSignalThread(Thread signalThread) {
		this.signalThread = signalThread;
	}

	
	
}
