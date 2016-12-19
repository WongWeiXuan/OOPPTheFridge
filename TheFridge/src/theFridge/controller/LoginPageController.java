package theFridge.controller;

import java.io.File;
import java.io.IOException;
//import javafx.stage.StageStyle;
//import javafx.application.Platform;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
	public void goToHomePage(ActionEvent event) throws IOException {
		String Username = tFUsername.getText();
		String Password = pFPassword.getText();
		
		if (Username.equals(" ") || Username.equals(null)) {
			comment.setText("Please fill in your username!");

		}
		else if (Password.equals(" ") || Password.equals(null)) {
			comment.setText("Please fill in your password!");
		}
		else {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
			
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			stage.setWidth(bounds.getWidth());
			stage.setHeight(bounds.getHeight());
			stage.setMaximized(true);
			stage.setScene(new Scene(root));
	 	    stage.show();
		}
		//Quack2 is the new Quack
		String quack = "src/theFridge/sound/quack2.mp3";

		Media sound = new Media(new File(quack).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	@FXML
	public void goToSignupPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/SignupPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
}
