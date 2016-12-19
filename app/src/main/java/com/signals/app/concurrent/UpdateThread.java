package com.signals.app.concurrent;

import com.signals.app.api.JunctionContextReaderService;
import com.signals.app.api.ScheduleReaderService;
import com.signals.common.utils.DateTimeUtils;
import com.signals.common.utils.ExcpetionUtils;
import com.signals.common.utils.ServiceLocator;


/**
 * 
 * @author Sovani
 * The thread is used to periodically read the context properties and scheduler properties
 * It gets interrupted by emergency even
 */
public class UpdateThread implements Runnable{
		private JunctionContext context;
		private ScheduleReaderService schedulerService;
		private JunctionContextReaderService contextReaderService;
	
		public UpdateThread(JunctionContext context){
			this.context = context;
			schedulerService = ServiceLocator.getScheduleReaderService();
			contextReaderService = ServiceLocator.getJunctionContextReaderService();
		}
	
		public void run() {
			
			try{
				System.out.println("Update thread started");
				while(true){
					synchronized(context){
						context.setDirty(true);
						schedulerService.loadScheduler();
						contextReaderService.loadContext(context);
						int duration = schedulerService.getSignalCycleDuration(DateTimeUtils.getCurrTime());
						if(duration == -1){
							context.getDefaultTime();
						}else{
							context.setTotalSignalTime(duration);
						}
						
						contextReaderService.loadContext(context);
						context.setDirty(false);
						System.out.println("Context updated");
						
						context.notify();
					}
					Thread.sleep(context.getUpdateInterval());
					
				}
			}catch(Exception e){
				System.out.println("Thread interrupted");
				ExcpetionUtils.printStackTrace(e);
				try{
					Thread.sleep(context.getEmergencyWaitDuration());
					context.wait();
				}catch(Exception e1){
					
				}
			}
		}
		
}
