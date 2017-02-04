package theFridge.controller;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NavigationFXMLController {
	//Navigation Button
	@FXML
	private VBox homeScene;
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
	//Navigation Bar
	@FXML 
	private VBox naviPreview;
	@FXML 
	private VBox navi;
	@FXML 
	private AnchorPane Anchor;
	
	//
	@FXML
	public void showNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0); //(naviXValue) Move navi from x = -150 to x = 0
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0); //(naviPreviewOpacity) Change naviPreview from opacity 1 to 0
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity); //1st KeyFrame with duration of 300ms
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void hideNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150); //(naviXValue) Move navi from x = 0 to x = -150
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1); //(naviPreviewOpacity) Change naviPreview from opacity 0 to 1
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity); //1st KeyFrame with duration of 300ms
		
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/FindingDish.fxml"));
		}
		else if(event.getSource().equals(compostScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		}
		else if(event.getSource().equals(foodScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/FoodCalculatorNavigation.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		stage.setMaximized(true);
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
}
