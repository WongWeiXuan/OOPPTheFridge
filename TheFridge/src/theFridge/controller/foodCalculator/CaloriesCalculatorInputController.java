package theFridge.controller.foodCalculator;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CaloriesCalculatorInputController {

    @FXML
    private JFXTextField calories;

    @FXML
    public Spinner<Integer> meals;

    @FXML
    private JFXButton goNext;

	@FXML 
	private Circle profileCircle;

	@FXML 
	private AnchorPane Anchor;

	@FXML 
	private VBox naviPreview;

	@FXML 
	private VBox homeScene;

	@FXML
	private VBox navi;

	@FXML 
	private VBox recipeScene;

	@FXML 
	private VBox compostScene;

	@FXML 
	private VBox foodScene;

	@FXML 
	private VBox quizScene;

	@FXML 
	private VBox prizeScene;
	
	@FXML
	private Circle Breakfast;
   
	@FXML
	private Circle Lunch;
	
	@FXML
	private Circle Dinner;
	
	@FXML
	private Circle Snacks;
	
    public static String NumOfMeals;
    public static int NumOfCalories;
    
    @FXML
    void initialize(){
    }
    
    @FXML
    void setMeal(MouseEvent event) throws IOException{
    	if(event.getSource().equals(Breakfast)){
    		NumOfMeals = "Breakfast";
    	}
    	else if(event.getSource().equals(Lunch)){
    		NumOfMeals = "Lunch";
    	}
    	else if(event.getSource().equals(Dinner)){
    		NumOfMeals = "Dinner";
    	}
    	else if(event.getSource().equals(Snacks)){
    		NumOfMeals = "Snacks";
    	}	
    }
    
    @FXML
    void goNext(ActionEvent event) throws IOException {
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

	@FXML 
	public void showUserDropdown(MouseEvent event) {}

	@FXML
	public void showNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void hideNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void changeScene(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = null; //(Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
		if(event.getSource().equals(homeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		}
		else if(event.getSource().equals(recipeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RecipePage.fxml"));
		}
		else if(event.getSource().equals(compostScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		}
		else if(event.getSource().equals(foodScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}