package com.signals.ui.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.signals.app.api.SignalManagementService;
import com.signals.common.utils.SignalStates;


/**
 * 
 * @author Sovani
 *  This class represents the signal on the UI
 */
public class Signal extends JPanel{
	private String signalId;
	
	private final JButton red, green, yellow;
	
	private SignalManagementService signalService;
	
	public Signal( String signalID){
		this.signalId = signalID;
		
		setSize(100,300);
		red = new JButton();
		yellow = new JButton();
		green = new JButton();
		this.add(red);
		this.add(yellow);
		this.add(green);
		JButton eventButton = new JButton("Ambulance/Police/Firebrigade");
		eventButton.addActionListener(new ActionListener()
			{
			 
				public void actionPerformed(ActionEvent e) {
					signalService.setEmergency(signalId);
				}
			});
		this.add(eventButton);
	}
	
	/**
	 * @author Sovani
	 * @param state - received from SignalManagementService
	 * Updates the signal color as per state of signal in SignalManagementService
	 */
	
	public void updateState(int state) {
		
		red.setBackground(state == SignalStates.RED.getValue() ? Color.RED : Color.WHITE);
		yellow.setBackground(state == SignalStates.YELLOW.getValue() ? Color.YELLOW : Color.WHITE);
		green.setBackground(state == SignalStates.GREEN.getValue() ? Color.GREEN : Color.WHITE);
	}

	public SignalManagementService getSignalService() {
		return signalService;
	}

	public void setSignalService(SignalManagementService signalService) {
		this.signalService = signalService;
	}

	public String getSignalId() {
		return signalId;
	}

}
