package theFridge.controller.foodCalculator;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

public class CaloriesCalculatorInputController {

    @FXML
    private JFXTextField calories;

    @FXML
    private Spinner<?> meals;

    @FXML
    private JFXButton goNext;

    @FXML
    void goNext(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculatorInput.fxml"));
		
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculator.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
    }

}
