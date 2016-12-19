package com.signals.app.api.impl;

import java.util.ArrayList;
import java.util.List;

import com.signals.app.api.SignalDuartionCalculationService;
import com.signals.app.concurrent.JunctionContext;
import com.signals.common.utils.DateTimeUtils;
import com.signals.common.utils.ServiceLocator;
import com.signals.ui.App;


/**
 * Service to calculate the signal duration based on
 * junction, signal,  roads to the junciotn, their contention at given time and max and min limits of duration on the signal
 * @author Sovani
 *
 */
public class SignalDurationCalculationServiceImpl implements SignalDuartionCalculationService{

	public int getGreenSignalDuration(String signalId, JunctionContext context) {
		int totalTime = context.getTotalSignalTime();
		String junctionId = ServiceLocator.getSignalservice().getJunctionId(signalId);
		String[] signals = ServiceLocator.getSignalservice().getSignalsForJunction(junctionId);
		int avgNumber = 0, totalContention = 0, contentionIndex = 0, routeContention = 0;
		List<Integer> contentionIndices = new ArrayList<Integer>();
		
		for(String signal: signals){
			String roadName = ServiceLocator.getSignalservice().getSignalRoadNameMap().get(signal);
			avgNumber = ServiceLocator.getTrafficdetailsservice().getAverageNoOfVehiclesOnRoad(roadName, DateTimeUtils.getCurrTime());
			int laneCount = ServiceLocator.getSignalservice().getSignalToLaneCount().get(signal);
			contentionIndex = avgNumber/laneCount;
			contentionIndices.add(new Integer(contentionIndex));
			totalContention = totalContention + contentionIndex;
			if(signal.equalsIgnoreCase(signalId)){
				routeContention = contentionIndex;
			}
		}
		
		int greenSignalDuration = totalTime*routeContention/totalContention;
		if(context.getMinSignalDuration() > greenSignalDuration){
			return context.getMinSignalDuration();
		}
		if(context.getMaxSignalDuration() < greenSignalDuration){
			return context.getMaxSignalDuration();
		}
		
		return greenSignalDuration;
	}

	
}
