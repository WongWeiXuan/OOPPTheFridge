package theFridge.model;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	        }				
	    }));
	    timeline.playFromStart();
	    
	    label.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.equals("0")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {
								@SuppressWarnings("rawtypes")
								Dialog dialog = new Dialog();
								Parent root;
								root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizExplanationPopup.fxml"));
								Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
								stage.initStyle(StageStyle.TRANSPARENT);
								Scene scene = new Scene(root);
								stage.setX(650);
								stage.setY(400);
								stage.setScene(scene);
								stage.showAndWait();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}});
				}
			}});
	}
	
	
	public static void main(String[] args) {
		
	}

}
