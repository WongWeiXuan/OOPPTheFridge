package theFridge.model;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Label;

public class CountdownTimer {
	private Timer timer;
	
	public void start(Label label) {
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			int i = 10;
			@Override
			public void run() {
				if (i == 0) {
					timer.cancel();
				}
				label.setText(String.valueOf(i));
				i--;
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	public void cancel() {
		timer.cancel();
	}
	
	public static void main(String[] args) {	   
		
	}
}
