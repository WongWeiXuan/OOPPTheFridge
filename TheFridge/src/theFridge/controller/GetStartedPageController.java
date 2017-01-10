package theFridge.controller;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ScrollPane;

public class GetStartedPageController {
	@FXML
	private VBox Profile;
	@FXML
	private VBox Recipes;
	@FXML
	private VBox Compost;
	@FXML
	private VBox Random;
	@FXML
	private VBox Quiz;
	@FXML
	private VBox Redeem;
	@FXML
	private VBox Shopping;
	@FXML
	private VBox Leftover;
	@FXML
	private VBox Logout;
	//ProfilePicture
	@FXML 
	private Circle profileCircle;
	//ScrollPanes
	@FXML 
	private ScrollPane ProfileScroll;
	@FXML 
	private ScrollPane RecipeScroll;
	@FXML 
	private ScrollPane CompostScroll;
	@FXML 
	private ScrollPane RandomScroll;
	@FXML 
	private ScrollPane QuizScroll;
	@FXML 
	private ScrollPane RedeemScroll;
	@FXML 
	private ScrollPane ShoppingScroll;
	@FXML 
	private ScrollPane LeftoverScroll;
	@FXML 
	private ScrollPane LogoutScroll;
	//For profile dropdown(Profile dropdown)
	@FXML 
	private StackPane dropdownMenu;
	@FXML 
	private VBox dropdownWord;
	@FXML 
	private VBox dropdownBackground;
	private boolean open = false;
	@FXML 
	private VBox ProfileMenu;
	@FXML 
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	
	public void initialize(){
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
	}

	@FXML public void hideExtraInfo(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue keyValue1 = new KeyValue(ProfileScroll.vvalueProperty(), 0); //Scroll to the top (0.0)
		KeyValue keyValue2 = new KeyValue(RecipeScroll.vvalueProperty(), 0);
		KeyValue keyValue3 = new KeyValue(CompostScroll.vvalueProperty(), 0);
		KeyValue keyValue4 = new KeyValue(RandomScroll.vvalueProperty(), 0);
		KeyValue keyValue5 = new KeyValue(QuizScroll.vvalueProperty(), 0);
		KeyValue keyValue6 = new KeyValue(RedeemScroll.vvalueProperty(), 0);
		KeyValue keyValue7 = new KeyValue(ShoppingScroll.vvalueProperty(), 0);
		KeyValue keyValue8 = new KeyValue(LeftoverScroll.vvalueProperty(), 0);
		KeyValue keyValue9 = new KeyValue(LogoutScroll.vvalueProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue1, keyValue2, keyValue3, keyValue4, keyValue5, keyValue6, keyValue7, keyValue8, keyValue9); //1st KeyFrame with duration of 300ms
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML 
	public void showExtraInfo(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue keyValue1 = null;
		
		if(event.getSource().equals(Profile)){
			keyValue1 = new KeyValue(ProfileScroll.vvalueProperty(), 1.0); //Scroll to the bottom
		}
		else if(event.getSource().equals(Recipes)){
			keyValue1 = new KeyValue(RecipeScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Compost)){
			keyValue1 = new KeyValue(CompostScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Random)){
			keyValue1 = new KeyValue(RandomScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Quiz)){
			keyValue1 = new KeyValue(QuizScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Redeem)){
			keyValue1 = new KeyValue(RedeemScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Shopping)){
			keyValue1 = new KeyValue(ShoppingScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Leftover)){
			keyValue1 = new KeyValue(LeftoverScroll.vvalueProperty(), 1.0);
		}
		else if(event.getSource().equals(Logout)){
			keyValue1 = new KeyValue(LogoutScroll.vvalueProperty(), 1.0);
		}
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue1); //1st KeyFrame with duration of 300ms
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
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
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
			}
			else if(event.getSource().equals(LogoutMenu)){
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
				stage.setMaximized(false);
			}
			stage.setScene(new Scene(root));
	 	    stage.show();
		}
	
	@FXML
	public void changeScene(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
		
		
		if(event.getSource().equals(Profile)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		}
		else if(event.getSource().equals(Recipes)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ViewRecipes.fxml"));
		}
		else if(event.getSource().equals(Compost)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Page1.fxml"));
		}
		else if(event.getSource().equals(Random)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculatorInput.fxml"));
		}
		else if(event.getSource().equals(Quiz)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizBeginPage.fxml"));
		}
		else if(event.getSource().equals(Redeem)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		else if(event.getSource().equals(Shopping)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/ShoppingListPage.fxml"));
		}
		else if(event.getSource().equals(Leftover)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/FindingDish.fxml"));
		}
		else if(event.getSource().equals(Logout)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			stage.setMaximized(false);
		}
		
 		stage.setScene(new Scene(root));
 	    stage.show();
	}

}
