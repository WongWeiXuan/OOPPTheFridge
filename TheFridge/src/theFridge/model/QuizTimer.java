package theFridge.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class QuizTimer {
	private Integer timeSeconds = 10;
	
	public void setTimer(Label label) {
		label.setText("10");
		Timeline timeline = new Timeline();
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
	    	@Override
			public void handle(ActionEvent event) {
	    		timeSeconds--;
	    		if (timeSeconds >= 0) {
	    			label.setText(timeSeconds.toString());
	    			System.out.println(timeSeconds.toString());
	    		}
	    		else {
	    			timeline.stop();
	    			System.out.println("Timer has stopped.");
	    		}
	        		/*if (timeSeconds <= 0) {
	        			System.out.println("Countdown timer stopped.");
	        			timeline.stop();
	        		}*/
	        	}				
	        }));
	    timeline.playFromStart();
	}
	
	
	public static void main(String[] args) {
		
	}

}
