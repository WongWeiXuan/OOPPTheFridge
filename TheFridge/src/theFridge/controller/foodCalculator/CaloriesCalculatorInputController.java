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
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class CaloriesCalculatorInputController {

    @FXML
    private JFXTextField calories;

    @FXML
    public Spinner<Integer> meals;

    @FXML
    private JFXButton goNext;
    
    public static int NumOfMeals;
    public static int NumOfCalories;
   
    
    @FXML
    void initialize(){
    	meals.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, 1));
    }
    
    @FXML
    void goNext(ActionEvent event) throws IOException {
    	NumOfMeals = (Integer) meals.getValue();
    	NumOfCalories = Integer.parseInt(calories.getText());
    	System.out.println("Con1 Number of Meals: " + NumOfMeals);
    	System.out.println("Con1 Number Of Calories: " + NumOfCalories);
    	//Change scene
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculatorInput.fxml"));
		
		root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculator.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
    }
}
