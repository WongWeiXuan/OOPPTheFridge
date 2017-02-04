package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.RecipeBook;
import theFridge.model.User;



public class ViewRecipesController {
	@FXML
	private Button viewrecipebtn;
	@FXML
	private Button findrecipebtn;
	@FXML
	private Button addrecipebtn;
	@FXML
	private Text recipenametxt;
	@FXML
	private Text ingre1txt;
	@FXML
	private Text ingre2txt;
	@FXML
	private Text ingre3txt;
	@FXML
	private TextField indexbox;
	@FXML
	private Button leftbtn;
	@FXML
	private Button rightbtn;
	@FXML
	private Button addnewrecipebtn;
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
	private VBox naviPreview1;
	@FXML
	private VBox foodScene;
	@FXML
	private VBox quizScene;
	@FXML
	private VBox prizeScene;
	@FXML
	private VBox navi1;
	@FXML
	private VBox homeScene1;
	@FXML
	private VBox recipeScene1;
	@FXML
	private VBox compostScene1;
	@FXML
	private VBox foodScene1;
	@FXML
	private VBox quizScene1;
	@FXML
	private VBox prizeScene1;
	@FXML
	private Button deleterecipebtn;
	@FXML
	private TextArea recipeimg;
	@FXML
	private TextArea stepstxt;
	@FXML
	private Text recipebytxt;
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
		User u = new User();
        u = u.getCurrentUser();
        //Image img = new Image(u.getProfileImage());
		//profileCircle.setFill(new ImagePattern(img));
		this.setRecipeBookList(RecipeBook.getAllRecipeBook());
	}
	// ASK MEMBERS TO IMPLEMENT THIS USERNAME GETTER!!'
		//
		//
		//
		//
		
		public String username = "PLS GET USERNAME";
		
		//
		//
		//
		//
		//
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
	//private Main mainApp;
	private ArrayList <RecipeBook> bookList;
	private int currIndex=-1;
	
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
		
	// Event Listener on Button[#leftbtn].onMouseClicked
	@FXML
	public void goLeft(MouseEvent event) {
		if (currIndex>0){
			currIndex--;
			showRecipe(bookList.get(currIndex));
		}
	}
	
	// Event Listener on Button[#rightbtn].onMouseClicked
	@FXML
	public void goRight(MouseEvent event) {
		if (currIndex>=0 && currIndex<bookList.size()-1){
			currIndex++;
			showRecipe(bookList.get(currIndex));
		}
	}
	
	
	public void showRecipe(RecipeBook f) {
			recipebytxt.setText(f.getAddedBy());
			recipenametxt.setText(f.getRecipeName());
			ingre1txt.setText(f.getIngredient1());
			ingre2txt.setText(f.getIngredient2());
			ingre3txt.setText(f.getIngredient3());
			stepstxt.setText(f.getSteps());
			indexbox.setText(currIndex +1 + " of " + bookList.size());	
	}
	
	// Event Listener on Button[#addnewrecipebtn].onMouseClicked
	@FXML
	public void addNewRecipe(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/EditRecipe.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	public void deleteRecipe(MouseEvent event) throws IOException {
		
		RecipeBook delBook = bookList.get(currIndex);
		
		//THIS CHECKS WHETHER YOU CREATED THIS RECIPE, AS YOU CAN ONLY DELETE YOUR OWN RECIPES
		if (username.equalsIgnoreCase(delBook.getAddedBy())){
		delBook.deleteRecipe();
		
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/ViewRecipes.fxml"));
		
		stage.setScene(new Scene(root));
 	    stage.show();
 	    
		}
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/QuizPage.fxml"));
		}
		else if(event.getSource().equals(prizeScene)){
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		}
		stage.setMaximized(true);
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
	//public void setMain(Main main) {
		//this.mainApp = mainApp;
	//}
	public void setRecipeBookList(ArrayList<RecipeBook> allRecipeBook) {
		if (allRecipeBook!=null && allRecipeBook.size()>0) {
			this.bookList = allRecipeBook;
			currIndex=0;
			showRecipe(bookList.get(0));
		}
		
	}
}
