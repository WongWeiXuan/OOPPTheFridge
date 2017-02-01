package theFridge.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.RecipeBook;

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
