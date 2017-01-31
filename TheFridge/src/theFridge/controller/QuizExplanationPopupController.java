package theFridge.controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import theFridge.model.QuizQuestionsModel;

public class QuizExplanationPopupController {
	@FXML
	private JFXButton dontShowAgain;
	@FXML
	private JFXButton gotItBtn;
	@FXML
	private Label contentLabel;

	public static String explanationText;
	
	@FXML
	public void initialize() {
		contentLabel.setText(explanationText);
	}
	
	@FXML
	public void closePopup(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void dontShowAgain(ActionEvent event) {
		QuizQuestionsModel.setDontShowAgain(false);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
}
