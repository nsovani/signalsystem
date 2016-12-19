package com.signals.app.model;

public class Signal{
	
	private String signalId;
	
	private Junction junction;
	
	public Signal(String signalId){
		this.signalId = signalId;
	}
	
	public Junction getJunction() {
		return junction;
	}

	public void setJunction(Junction junction) {
		this.junction = junction;
	}
	
	
	public String getSignalId() {
		return signalId;
	}

	public void setSignalId(String signalId) {
		this.signalId = signalId;
	}
	

}
