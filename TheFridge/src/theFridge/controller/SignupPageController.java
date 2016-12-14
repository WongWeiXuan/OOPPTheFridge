package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class SignupPageController {
	@FXML
	private JFXButton loginBtn;
	@FXML
	private JFXButton createAccount;
	@FXML
	private JFXTextField tFUsername;
	@FXML
	private JFXTextField tFEmail;
	@FXML
	private JFXPasswordField pFPassword;
	
	@FXML
	void goToLoginPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
	@FXML
	void createAccount(ActionEvent event) {
		String Username = tFUsername.getText();
		String Password = pFPassword.getText();
		String Email = tFEmail.getText();
		
		if (Username.equals(" ") || Username.equals(null)) {
			tFUsername.setPromptText("Please fill in your username!");

		}
		else if (Password.equals(" ") || Password.equals(null)) {
			pFPassword.setPromptText("Please fill in your password!");
		}
		else if (Email.equals(" ") || Email.equals(null)) {
			tFEmail.setPromptText("Please fill in your email!");
		}
		else {
			
		}
	}
}
