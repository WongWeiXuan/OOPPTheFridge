package theFridge.controller.foodCalculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;

public class FoodCalculatorNavigationController {
	@FXML
	private JFXButton Calculator;
	@FXML
	private JFXButton Shopping;

	// Event Listener on JFXButton[#Calculator].onAction
	@FXML
	public void goFood(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = null;
		if(event.getSource().equals(Calculator)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculatorInput.fxml"));
		}
		else if(event.getSource().equals(Shopping)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		}
		stage.setScene(new Scene(root));
		stage.show();
			
	}
}
