package com.signals.app.api;

import com.signals.app.concurrent.JunctionContext;

/**
 * 
 * @author Niranjan Sovani
 * 
 * Read configuration and set context for a junction 
 *
 */
public interface JunctionContextReaderService {
	public void loadContext(JunctionContext context);
}
