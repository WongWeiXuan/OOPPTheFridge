package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.RecipeBook;
import theFridge.model.User;

public class FindingDishController {

	@FXML
	private Button viewrecipebtn;
	@FXML
	private Button findrecipebtn;
	@FXML
	private Button addrecipebtn;
	@FXML
	private TextField ingre1txt;
	@FXML
	private TextField ingre2txt;
	@FXML
	private TextField ingre3txt;
	@FXML
	private Button rightSubmitBtn;
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
	private TextField searchrecipename;
	@FXML
	private Button leftSubmitBtn;

	public static String recipeName;
	public static ArrayList <RecipeBook> bookList;
	public static String ingre1;	
	public static String ingre2;
	public static String ingre3;
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

	//Show profile image
	@FXML
	public void initialize() throws FileNotFoundException{
		User user = new User();
		user = user.getCurrentUser();
        String myface = user.getProfileImage();
		String gf = "/theFridge/picture/head.png";
	        if (myface.equals("null")) {
	            Image dd = new Image(gf);
	            profileCircle.setFill(new ImagePattern(dd));
	        }
	        else {
	            Image image21 = new Image(myface);
	            profileCircle.setFill(new ImagePattern(image21));
	        }
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
		User user = new User();
		user = user.getCurrentUser();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = null; //(Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/HomePage.fxml"));
		
		
		if(event.getSource().equals(ProfileMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile.fxml"));
		}
		else if(event.getSource().equals(SettingMenu)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/Profile2.fxml"));
		}
		else if(event.getSource().equals(LogoutMenu)){
			user.setRememberMe(false);
			user.updateUser();
			
			stage.setX(450);
			stage.setY(128);
			stage.setWidth(1020);
			stage.setHeight(650);
			
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			stage.setMaximized(false);
		}
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	// Event Listener on Button[#viewrecipebtn].onMouseClicked
		@FXML
		public void viewRecipe(MouseEvent event) throws IOException {
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
		
	// Event Listener on Button[#rightSubmitBtn].onMouseClicked
	@FXML
	public void handleSearchIngredient(MouseEvent event) throws IOException {
		String SI1 = ingre1txt.getText();
		String SI2 = ingre2txt.getText();
		String SI3 = ingre3txt.getText();
		
		ingre1 = ingre1txt.getText();
		ingre2 = ingre2txt.getText();
		ingre3 = ingre3txt.getText();
		
		@SuppressWarnings("unused")
		RecipeBook grb = new RecipeBook();
		ArrayList <RecipeBook> sortedByIngredientBookList = new ArrayList<RecipeBook>();
		sortedByIngredientBookList = RecipeBook.getAllRecipeBook();
		
		for (int i=0;i<sortedByIngredientBookList.size();i++){
			
			String CI1 = sortedByIngredientBookList.get(i).getIngredient1();
			String CI2 = sortedByIngredientBookList.get(i).getIngredient2();
			String CI3 = sortedByIngredientBookList.get(i).getIngredient3();
			
			if(CI1.contains(SI1) && CI2.contains(SI2) && CI3.contains(SI3)){
				sortedByIngredientBookList.get(i).setScore(3);
			}
			
			else if(CI1.contains(SI1) && CI2.contains(SI2)){
				sortedByIngredientBookList.get(i).setScore(2);
			}
			
			else if(CI2.contains(SI2) && CI3.contains(SI3)){
				sortedByIngredientBookList.get(i).setScore(2);
			}
			else if(CI1.contains(SI1) && CI3.contains(SI3)){
				sortedByIngredientBookList.get(i).setScore(2);
			}
			
			else if(CI1.contains(SI1) || CI2.contains(SI2) || CI3.contains(SI3)){
				sortedByIngredientBookList.get(i).setScore(1);
			}
		}
		
		//Sorting method here
		Collections.sort(sortedByIngredientBookList, new RecipeBookComparator());
		// bookList is now sorted. need to view it.
		FindingDishController.bookList= sortedByIngredientBookList;
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/FoundDish.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
		
	}
	
	
	// Event Listener on Button[#leftSubmitBtn].onMouseClicked

	@FXML
	public void handleSearchName(MouseEvent event) throws IOException {
		recipeName = searchrecipename.getText();
		
		@SuppressWarnings("unused")
		RecipeBook grb = new RecipeBook();
		bookList = RecipeBook.getAllRecipeBook();
		ArrayList <RecipeBook> sortedByNameBookList = new ArrayList<RecipeBook>();
		
		for (int i=0;i<bookList.size();i++){
			String sub = bookList.get(i).getRecipeName();
		//	if(recipeName.contains(sub)){  is reversed.
			if(sub.contains(recipeName)){
				sortedByNameBookList.add(bookList.get(i));
			}
		}
		
		FindingDishController.bookList= sortedByNameBookList;
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/FoundDishForSearchByName.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
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
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/FoodCalculatorNavigation.fxml"));
			}
			else if(event.getSource().equals(quizScene)){
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizBeginPage.fxml"));
			}
			else if(event.getSource().equals(prizeScene)){
				root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
			}
			stage.setMaximized(true);
	 		stage.setScene(new Scene(root));
	 	    stage.show();
		}

}
