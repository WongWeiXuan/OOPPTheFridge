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
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EditRecipeController {
	@FXML
	private Button viewrecipebtn;
	@FXML
	private Button findrecipebtn;
	@FXML
	private Button addrecipebtn;
	@FXML
	private TextField indexbox;
	@FXML
	private Button leftbtn;
	@FXML
	private Button rightbtn;
	@FXML
	private TextField recipenametxt;
	@FXML
	private TextField ingre1txt;
	@FXML
	private TextField ingre2txt;
	@FXML
	private TextField ingre3txt;
	@FXML
	private Button imgbrowsebtn;
	@FXML
	private Button submitbtn;
	@FXML
	private VBox naviPreview;
	@FXML
	private VBox navi;
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
	@FXML
	private TextArea stepstxt;

	// Event Listener on Button[#viewrecipebtn].onMouseClicked
	@FXML
	public void viewRecipe(MouseEvent event) throws IOException  {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/ViewRecipes.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on Button[#findrecipebtn].onMouseClicked
	@FXML
	public void findRecipe(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/FindingDish.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on Button[#addrecipebtn].onMouseClicked
	@FXML
	public void addRecipe(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/EditRecipe.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on Button[#leftbtn].onMouseClicked
	@FXML
	public void goLeft(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#rightbtn].onMouseClicked
	@FXML
	public void goRight(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#imgbrowsebtn].onMouseClicked
	@FXML
	public void browseImg(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#submitbtn].onMouseClicked
	@FXML
	public void handleSubmit(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on VBox[#naviPreview].onMouseEntered
	@FXML
	public void showNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0); //(naviXValue) Move navi from x = -150 to x = 0
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0); //(naviPreviewOpacity) Change naviPreview from opacity 1 to 0
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity); //1st KeyFrame with duration of 300ms
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	// Event Listener on VBox[#navi].onMouseExited
	@FXML
	
	public void hideNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150); //(naviXValue) Move navi from x = 0 to x = -150
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1); //(naviPreviewOpacity) Change naviPreview from opacity 0 to 1
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity); //1st KeyFrame with duration of 300ms
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	// Event Listener on VBox[#homeScene].onMouseClicked
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		}
		else if(event.getSource().equals(quizScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource(""));
		}
		stage.setMaximized(true);
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}