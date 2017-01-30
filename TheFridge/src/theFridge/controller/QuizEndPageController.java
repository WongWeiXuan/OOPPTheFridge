package theFridge.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.QuizQuestionsModel;

public class QuizEndPageController {
	@FXML
	private AnchorPane Anchor;
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
	private Circle profileCircle;
	@FXML 
	private StackPane dropdownMenu;
	@FXML 
	private VBox dropdownWord;
	@FXML 
	private VBox dropdownBackground;
	@FXML 
	private VBox ProfileMenu;
	@FXML 
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	@FXML
	private JFXButton tryAgainBtn;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private Label scoreLabel;
	@FXML
	private LineChart<?, ?> lineChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private Label pointsLabel;
	
	//For profile dropdown (Profile dropdown)
	private boolean open = false;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void initialize() {
		//Show profile image
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
		
		XYChart.Series series = new XYChart.Series();
		series.setName("My Quiz Details");
		series.getData().add(new XYChart.Data("1", 10));
		series.getData().add(new XYChart.Data("2", 20));
		series.getData().add(new XYChart.Data("3", 30));
		series.getData().add(new XYChart.Data("4", 40));
		series.getData().add(new XYChart.Data("5", 50));
		series.getData().add(new XYChart.Data("6", 60));
		series.getData().add(new XYChart.Data("7", 70));
		series.getData().add(new XYChart.Data("8", 80));
		series.getData().add(new XYChart.Data("9", 90));
		series.getData().add(new XYChart.Data("10", 100));
		lineChart.getData().add(series);
		
		progressIndicator.setProgress(QuizQuestionsModel.getPointsAttained() / 10);
		scoreLabel.setText((QuizQuestionsModel.getPointsAttained() / 10) + "/10 questions answered correctly");
		pointsLabel.setText(String.valueOf(QuizQuestionsModel.getPointsAttained()));
	}
	
	@FXML
	public void goToQuizBegin(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizBeginPage.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	//Animation for the Dropdown(Profile Dropdown)
	@FXML
	public void showUserDropdown(MouseEvent event) {
		RotateTransition rt = new RotateTransition(Duration.millis(400), profileCircle);
		if(open == false){
			Timeline timeline = new Timeline();
			rt.setByAngle(-360);
			rt.play();
			KeyValue CircleLeft = new KeyValue(profileCircle.translateXProperty(), -46);
			KeyValue MenuHalf = new KeyValue(dropdownMenu.translateXProperty(), -150); //Move dropdownMenu(dropdownWord & dropdownBackground) translateX to -100
			KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), MenuHalf, CircleLeft); //1st KeyFrame with duration of 300ms
			//Wait
			KeyFrame WaitingFrame1 = new KeyFrame(
				Duration.millis(200), 
				first -> {
					Timeline timeline3 = new Timeline();
					KeyValue MenuDown = new KeyValue(dropdownMenu.translateYProperty(), 255); //Move dropdownMenu(dropdownWord & dropdownBackground) translateY to 135
					KeyValue ShowWord = new KeyValue(dropdownWord.opacityProperty(), 1);
					KeyFrame keyFrame3 = new KeyFrame(Duration.millis(200), MenuDown, ShowWord); //1st KeyFrame with duration of 300ms
					
					timeline3.getKeyFrames().addAll(keyFrame3);
					timeline3.play();
				});
						
			open = true;
			timeline.getKeyFrames().addAll(keyFrame1, WaitingFrame1);
			timeline.play();
		}
		else if(open == true){
			Timeline timeline = new Timeline();
			rt.setByAngle(360);
			rt.play();
			KeyValue MenuDown = new KeyValue(dropdownMenu.translateYProperty(), 0); //Move dropdownMenu(dropdownWord & dropdownBackground) translateY to 135
			KeyValue HideWord = new KeyValue(dropdownWord.opacityProperty(), 0);
			KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), MenuDown, HideWord); //1st KeyFrame with duration of 300ms
			//Wait
			KeyFrame WaitingFrame1 = new KeyFrame(
				Duration.millis(200), 
				first -> {
					Timeline timeline3 = new Timeline();
					KeyValue CircleRight = new KeyValue(profileCircle.translateXProperty(), 0);
					KeyValue MenuHalf = new KeyValue(dropdownMenu.translateXProperty(), 0); //Move dropdownMenu(dropdownWord & dropdownBackground) translateX to -100
					KeyFrame keyFrame3 = new KeyFrame(Duration.millis(200), MenuHalf, CircleRight); //1st KeyFrame with duration of 300ms
					
					timeline3.getKeyFrames().addAll(keyFrame3);
					timeline3.play();
				});
						
			open = false;
			timeline.getKeyFrames().addAll(keyFrame1, WaitingFrame1);
			timeline.play();
		}
	}
	
	//Change scene for dropdown(Profile dropdown)
	@FXML public void menuChangeScene(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = null; //(Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
		if(event.getSource().equals(ProfileMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		}
		else if(event.getSource().equals(SettingMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		}
		else if(event.getSource().equals(LogoutMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			stage.setMaximized(false);
		}
		stage.setScene(new Scene(root));
 	    stage.show();
	}

	@FXML
	public void showNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}

	@FXML
	public void hideNavigation(MouseEvent event) {
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/GetStartedPage.fxml"));
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizBeginPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}

 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}
