package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
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
	private JFXTextField tFPassword;
	@FXML
	private Label usernameAlert;
	@FXML
	private Label emailAlert;
	@FXML
	private Label passwordAlert;
	@FXML
	private TextFlow tFlowUsernameAlert;
	@FXML
	private TextFlow tFlowEmailAlert;
	@FXML
	private TextFlow tFlowPasswordAlert;
	
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
