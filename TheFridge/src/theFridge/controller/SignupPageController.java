package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignupPageController {
	@FXML
	private JFXButton loginBtn;
	@FXML
	private JFXButton createAccount;
	
	@FXML
	void goToLoginPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	@FXML
	void createAccount(ActionEvent event) {
		
	}
}
