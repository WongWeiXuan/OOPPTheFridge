package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

//import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class LoginPageController {
	@FXML
	private JFXButton loginBtn;
	@FXML
	private JFXButton signupBtn;
	@FXML
	private JFXButton minimiseBtn;
	@FXML
	private JFXButton closeBtn;
	
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
	void goToHomePage(ActionEvent event) throws IOException {
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
	
	@FXML
	void goToSignupPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/SignupPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
}
