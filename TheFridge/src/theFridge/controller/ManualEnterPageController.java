package theFridge.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;

public class ManualEnterPageController {
	@FXML
	private ImageView deleteBtn;
	@FXML
	private HBox addItemHbox;
	@FXML
	private JFXButton closeBtn;
	@FXML
	private JFXButton submitbtn;
	@FXML 
	private VBox addVbox;
	@FXML 
	private Spinner<Integer> firstSpinner;
	
	@FXML
	public void initialize(){
		firstSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
	}
	
	// Event Listener on JFXButton[#closeBtn].onAction
	@FXML
	public void closeStage(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	// Event Listener on JFXButton[#submitbtn].onAction
	@FXML
	public void submitFoodItems(ActionEvent event) {
		
	}
}
