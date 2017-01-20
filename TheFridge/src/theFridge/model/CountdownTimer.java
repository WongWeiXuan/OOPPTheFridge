package theFridge.model;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	private int secondsLeft;
	private Timer timer;
	
	public void start() {
		String secs = "10";
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    secondsLeft = Integer.parseInt(secs);
	    System.out.println(secondsLeft);
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	        	if (secondsLeft == 0) {
	     	        timer.cancel();
	     	        return;
	        	 }
	        	 secondsLeft--;
	        	 System.out.println(secondsLeft);

	        }
	    }, delay, period);
	}
	
	public static void main(String[] args) {	   
		CountdownTimer countDown = new CountdownTimer();
		countDown.start();
	}
}
