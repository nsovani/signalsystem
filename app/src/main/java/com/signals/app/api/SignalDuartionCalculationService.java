package com.signals.app.api;

import com.signals.app.concurrent.JunctionContext;

/**
 * 
 * @author Sovani
 * Service to calculate the duration for a given signal in a junction
 *
 */
public interface SignalDuartionCalculationService {
	public int getGreenSignalDuration(String signalId, JunctionContext context);
}
