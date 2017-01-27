package theFridge.controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class QuizExplanationPopupController {
	@FXML
	private JFXButton dontShowAgain;
	@FXML
	private JFXButton gotItBtn;
	@FXML
	private Label contentLabel;

	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void closePopup(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void dontShowAgain() {
		
	}
}
