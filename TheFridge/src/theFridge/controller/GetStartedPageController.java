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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
	
	public void initialize(){
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
	}

	@FXML public void hideExtraInfo(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue keyValue1 = new KeyValue(ProfileScroll.vvalueProperty(), 0);
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
			keyValue1 = new KeyValue(ProfileScroll.vvalueProperty(), 1.0);
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
	
	@FXML 
	public void showProfileDropdown(MouseEvent event) {
		//TO-DO
	}
	
	@FXML
	public void changeScene(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
		
		
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginPage.fxml"));
			stage.setMaximized(false);
		}
		
 		stage.setScene(new Scene(root));
 	    stage.show();
	}

}
