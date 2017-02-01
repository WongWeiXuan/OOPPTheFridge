package theFridge.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import theFridge.model.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import com.jfoenix.controls.JFXButton;

public class DonationPageController {
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
	private Text OrganizationTxt;
	@FXML
	private JFXRadioButton ManualEnterRadio;
	@FXML
	private ToggleGroup food;
	@FXML
	private JFXRadioButton ManualSelectRadio;
	@FXML
	private JFXRadioButton AutomaticRadio;
	@FXML
	private Text timeLbl;
	@FXML
	private StackPane dropdownMenu;
	@FXML
	private VBox dropdownBackground;
	@FXML
	private VBox dropdownWord;
	@FXML
	private VBox ProfileMenu;
	@FXML
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	@FXML
	private Circle profileCircle;
	//For profile dropdown(Profile dropdown)
	private boolean open = false;
	@FXML 
	private JFXButton submitBtn;
	@FXML 
	private JFXButton resetBtn;
	@FXML
	private JFXTextField nameField;
	@FXML
	private JFXTextField emailField;
	@FXML
	private JFXTextField contactField;

	//Show profile image
	@FXML
	public void initialize() throws FileNotFoundException{
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			@Override
			public void run(){
				Date date = new Date();
				timeLbl.setText(dateFormat.format(date));
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
		
		User user = new User();
		user = user.getCurrentUser();
		nameField.setText(user.getName());
		emailField.setText(user.getEmail());
	}
	@FXML 
	public void openPopup(ActionEvent event) throws IOException {
		Dialog<?> dialog = new Dialog<Object>();
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		Parent root = null;
		if(event.getSource().equals("ManualEnterRadio")){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ManualEnterPage.fxml"));
		}
		else if(event.getSource().equals("ManualSelectRadio")){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ManualSelectPage.fxml"));
		}
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML 
	public void showFood(ActionEvent event) {
		
	}
	
	@FXML 
	public void cancelDonation(ActionEvent event) {
		
	}
	
	@FXML 
	public void clearAllFields(ActionEvent event) {
		nameField.clear();
		emailField.clear();
		contactField.clear();
	}
	
	@FXML 
	public void submitDonation(ActionEvent event) {
		
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