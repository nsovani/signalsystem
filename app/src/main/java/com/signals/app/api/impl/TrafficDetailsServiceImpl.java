package com.signals.app.api.impl;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.signals.app.api.TrafficDetailsService;
import com.signals.common.utils.ExcpetionUtils;
import com.signals.common.utils.ServiceLocator;

/**
 * Implementation of traffic details service which provides the traffic related data and also populates the model
 * @author Sovani
 *
 */
public class TrafficDetailsServiceImpl implements TrafficDetailsService {
	
	public void init(String signalmapping, String signallanemap){
		loadSignalRoadNameMap(signalmapping);
		loadSignalLaneMap(signallanemap);
	}
	
	private void loadSignalLaneMap(String signallanemapfile){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			
			InputStream is = getClass().getClassLoader().getResourceAsStream(signallanemapfile);
			prop.load(is);
			Set<Object> signalids = prop.keySet();
			for(Object signalId: signalids){
				Map<String, Integer> map = ServiceLocator.getSignalservice().getSignalToLaneCount();
				Integer lanecount = Integer.parseInt((String)prop.get((String)signalId));
				map.put((String)signalId, lanecount);
			}
		}catch(Exception e){
			ExcpetionUtils.printStackTrace(e);
		}
	}
	
	
	private void loadSignalRoadNameMap(String mappingfile){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			
			InputStream is = getClass().getClassLoader().getResourceAsStream(mappingfile);
			prop.load(is);
			Set<Object> signalids = prop.keySet();
			for(Object signalId: signalids){
				Map<String, String> map = ServiceLocator.getSignalservice().getSignalRoadNameMap();
				map.put((String)signalId, (String)prop.get((String)signalId));
			}
		}catch(Exception e){
			ExcpetionUtils.printStackTrace(e);
		}
	}
	
//	public TrafficDetailsServiceImpl(){
//		Map<String, String> map = ServiceLocator.getSignalservice().getSignalRoadNameMap();
//		map.put("Road1", "Motibazar road - to");
//		map.put("Road2", "Station road - to");
//		map.put("Road3", "Motibazar road - from");
//		map.put("Road4", "Station road - from");
//		
//		Map<String, Integer> laneMap = ServiceLocator.getSignalservice().getSignalToLaneCount();
//		laneMap.put("Road1", 2);
//		laneMap.put("Road2", 1);
//		laneMap.put("Road3", 2);
//		laneMap.put("Road4", 1);
//	}

	public int getAverageNoOfVehiclesOnRoad(String routename,String currtime) {
		double val = Math.random()*100;
		return (int)val;
	}

}
