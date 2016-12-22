package theFridge.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import javafx.stage.StageStyle;
//import javafx.application.Platform;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginPageController {
	@FXML
	private JFXButton loginBtn;
	@FXML
	private JFXButton signupBtn;
	@FXML
	private JFXButton minimiseBtn;
	@FXML
	private JFXButton closeBtn;
	@FXML
	private JFXTextField tFUsername;
	@FXML
	private JFXPasswordField pFPassword;
	@FXML
	private JFXCheckBox loginCheckBox;
	@FXML
	private Label comment;
	@FXML
	private JFXSpinner spinner;
	
	/*
	@FXML
	void closePlatform(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
    void minimise(ActionEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
    }
    */
	
	@FXML
	public void goToHomePage(ActionEvent event) throws IOException, ParseException {
		String Username = tFUsername.getText();
		String Password = pFPassword.getText();
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(new FileReader("src/theFridge/file/people.json"));
		JSONObject jsonObject = (JSONObject) obj;
		
		JSONArray usernameArray = (JSONArray) jsonObject.get("Username"); 
		usernameArray.add(Username);
		
		JSONArray passwordArray = (JSONArray) jsonObject.get("Password"); 
		passwordArray.add(Password);
		
		//Input username validation
		if (Username.equals(" ") || Username.equals(null)) {
			comment.setText("Please fill in your username!");
		}
		//Input password validation
		else if (Password.equals(" ") || Password.equals(null)) {
			comment.setText("Please fill in your password!");
		}
		                           //This is a comment so you can login without entering anything for now
		if (!Username.equals("") /*|| !Username.equals(null)*/ && !Password.equals("") /*|| !Password.equals(null)*/) {
			//Checking JSON username
			for (int i = 0; i < usernameArray.size(); i++) {
				if (!Username.equals(usernameArray.get(i))) {
					comment.setText("Error! You are not registered yet.");
				}
			}
			//Checking JSON password
			for (int i = 0; i < passwordArray.size(); i++) {
				if (!Password.equals(passwordArray.get(i))) {
					comment.setText("Error! You are not registered yet.");
				}
			}
		}
		else {
			
			//Making a spinner upon clicking login
			spinner.setOpacity(1);
			loginBtn.setOpacity(0);
			
			Timeline timeline = new Timeline();
			KeyFrame keyFrame = new KeyFrame(
					Duration.seconds(2), 
					first -> {
							Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
							Parent root = null;
							try {
								root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Screen screen = Screen.getPrimary();
							Rectangle2D bounds = screen.getVisualBounds();
							stage.setX(bounds.getMinX());
							stage.setY(bounds.getMinY());
							stage.setWidth(bounds.getWidth());
							stage.setHeight(bounds.getHeight());
							stage.setMaximized(true);
							stage.setScene(new Scene(root));
					 	    stage.show();
							
					 	    //Quack2 is the new Quack
							String quack = "src/theFridge/sound/quack2.mp3";

							Media sound = new Media(new File(quack).toURI().toString());
							MediaPlayer mediaPlayer = new MediaPlayer(sound);
							mediaPlayer.play();
					});
	    	timeline.getKeyFrames().addAll(keyFrame);
			timeline.play();
		}
	}
	
	@FXML
	public void goToSignupPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/SignupPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
}
