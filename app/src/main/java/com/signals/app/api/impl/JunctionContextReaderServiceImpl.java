package com.signals.app.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.signals.app.api.JunctionContextReaderService;
import com.signals.app.concurrent.JunctionContext;
/**
 * Service implementation to read the junction configuration and set it into context
 * @author Sovani
 *
 */
public class JunctionContextReaderServiceImpl implements JunctionContextReaderService{
	
	private String contextPropertiesPath;
	
	public void loadContext(JunctionContext context){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			//input = new FileInputStream(contextPropertiesPath);
			InputStream is = getClass().getClassLoader().getResourceAsStream(contextPropertiesPath);
			prop.load(is);
			context.setYellowPercentage(Double.parseDouble((String)prop.get("YellowToGreenRatio")));
			context.setMaxSignalDuration(Integer.parseInt((String)prop.get("MaxSignalDuration")));
			context.setMinSignalDuration(Integer.parseInt((String)prop.get("MinSignalDuration")));
			context.setEmergencyWaitDuration(Integer.parseInt((String)prop.get("EmergencyWaitDuration")));
			context.setUpdateInterval(Integer.parseInt((String)prop.get("UpdateInterval")));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String getContextPropertiesPath() {
		return contextPropertiesPath;
	}

	public void setContextPropertiesPath(String contextPropertiesPath) {
		this.contextPropertiesPath = contextPropertiesPath;
	}
	
}
