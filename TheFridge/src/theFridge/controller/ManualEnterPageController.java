package theFridge.controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import theFridge.model.DonationPageModel;
import theFridge.model.ListModel;

public class ManualEnterPageController {
	@FXML
	private ImageView deleteBtn;
	@FXML
	private JFXButton closeBtn;
	@FXML
	private JFXButton submitbtn;
	@FXML 
	private VBox addVbox;
	@FXML 
	private Spinner<Integer> firstSpinner;
	@FXML 
	private TextField foodNameField;
	@FXML 
	private Text warningText;
	
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
		String name = foodNameField.getText();
		Integer amount = firstSpinner.getValue();
		if(name.equals(null) || name.equals("")){
			warningText.setText("Please enter a valid food item");
		}
		else if(amount.equals(null)){
			warningText.setText("Please enter the amount");
		}
		else{
			DonationPageModel idk = new DonationPageModel();
			ListModel lm = new ListModel(name, Double.parseDouble(String.valueOf(amount)));
			idk.addFood(lm);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}
	}
}
