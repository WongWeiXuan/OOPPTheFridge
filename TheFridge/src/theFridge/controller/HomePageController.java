package theFridge.controller;

import java.io.IOException;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;

public class HomePageController {
	
	@FXML
	private Text text;
    @FXML
    private JFXButton getStartedBtn;
    
    boolean run = true;

	@FXML 
	public void showWord(MouseEvent event) {
		String word = text.getText();
		
		while(run  == true){
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	randomiseWord(10, 1000, word);
			    }
			}));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.play();
			run = false;
		}
	}
	
	public void randomiseWord(int numOfLetters, int duration, String word){
		String Characters[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		Random rand = new Random();
		
		String displayWord = "";
		
		for(int a=0; a < numOfLetters; a++){
			int randomNum = rand.nextInt(36);
			displayWord += Characters[randomNum];
		}
		text.setText(displayWord);
		
	}

	@FXML 
	public void changeGetStartedScene(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/GetStartedPage.fxml"));
		stage.setMaximized(true);
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}
