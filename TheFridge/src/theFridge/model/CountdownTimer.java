package theFridge.model;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	private int interval;
	private Timer timer;
	
	public void start() {
		String secs = "10";
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    interval = Integer.parseInt(secs);
	    System.out.println(secs);
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	        	if (interval == 0) {
	     	        timer.cancel();
	     	        return;
	        	 }
	        	 interval--;
	        	 System.out.println(interval);

	        }
	    }, delay, period);
	}
	
	public static void main(String[] args) {	   
		CountdownTimer countDown = new CountdownTimer();
		countDown.start();
	}
}
