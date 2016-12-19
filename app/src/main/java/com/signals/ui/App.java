package com.signals.ui;

import java.awt.GridLayout;

import com.signals.app.api.SignalManagementService;
import com.signals.common.utils.ServiceLocator;
import com.signals.ui.model.Junction;
import com.signals.ui.model.Signal;


/**
 * @author Sovani
 * 
 * UI main app is a entry point of the appliction, it needs to be given following inputs as args
 * a .txt filename - It shoud have scheduler records as space separated records of "start-time end-time duration" 
 * 					One record should be on one line, duration in milliseconds for example "10:25 12:35 20000"
 * a .properties filename - It has properties for a single junction and servs as context setting for that junction
 * a .properties filename - It has properties that map signalid to road id
 * a .properties filename - It has properties that map signalid to number of lanes for corresponding route
 * 
 *  The application in general treats all the settings for time in millisecond
 *  The junction, signals in the junction are identified by string ids
 *  
 *  A UI thread constantly polls the backend system via service to get all the signal states (Green,Yellow,Red)
 *  Two backend threads are run for each junction added, one for executing business logic and other for updating junction settings and contexts
 *  To map the event of emergency such as police van ,ambulance, fire brigade a button each given for each signal on UI
 *  
 *  The package/s com.signals.ui.* deal with Java Swing functionality
 *  The package/s com.signals.app.* deal with the data model and business logic
 *  The package/s com.signals.common.* deal with common utility functions
 *  
 *  The application deals with the minimum functionality and input validations are not implemented
 *
 */
public class App 
{

    public static void main( String[] args )
    {
    	ServiceLocator.init(args);
    	SignalManagementService signalService = ServiceLocator.getSignalservice();
    	
    	Junction junction = new Junction("Hingane Square", signalService);
    	    	
    	Signal signal1 = new Signal("Road1");
    	signal1.setSignalService(signalService);
    	Signal signal2 = new Signal("Road2");
    	signal2.setSignalService(signalService);
    	Signal signal3 = new Signal("Road3");
    	signal3.setSignalService(signalService);
    	Signal signal4 = new Signal("Road4");
    	signal4.setSignalService(signalService);
        
        junction.setLayout(new GridLayout(4, 1));
        
        junction.add(signal1);
        junction.add(signal2);
        junction.add(signal3);
        junction.add(signal4);
        
        junction.setVisible(true);
        
        junction.activate();
    }
	
}
