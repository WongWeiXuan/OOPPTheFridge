package theFridge.controller;

import java.io.IOException;

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

public class GetStartedPageController {
	@FXML
	private VBox Profile;
	@FXML
	private VBox Recipes;
	@FXML
	private VBox Compost;
	@FXML
	private VBox Calculator;
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
	@FXML 
	private Circle profileCircle;
	
	public void initialize(){
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
	}
	
	@FXML 
	public void showProfileDropdown(MouseEvent event) {
		//TO-DO
	}
	
	// Event Listener on VBox[#Profile].onMouseClicked
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
		else if(event.getSource().equals(Calculator)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/FoodCalculatorCaloriesCalculatorRecipe.fxml"));
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
