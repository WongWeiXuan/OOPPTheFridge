package theFridge.controller.foodCalculator;

import java.awt.Insets;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.DAO.ShoppingListDAO;
import theFridge.model.FoodCalculatorCaloriesCalculatorRecipeInstructionModel;
import theFridge.model.FoodCalculatorCaloriesCalculatorRecipeModel;
import theFridge.model.Ingredient;
import theFridge.model.IngredientModel;

import com.jfoenix.controls.JFXButton;

public class CaloriesCalculatorController {
	@FXML
	private Circle profileCircle;
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
	private VBox vboxContainer;
	@FXML 
	private VBox Recipe;
	@FXML 
	private Label Time;
	@FXML 
	private Label NumberOfCalories;
	@FXML 
	private Label Measurements;
	@FXML 
	private Label FoodName;
	@FXML 
	private ImageView FoodImage;
	//Profile thingy...
	@FXML 
	private StackPane dropdownMenu;
	@FXML 
	private VBox dropdownWord;
	@FXML
	private VBox ProfileMenu;
	@FXML 
	private VBox SettingMenu;
	@FXML 
	private VBox LogoutMenu;
	@FXML
	private VBox dropdownBackground;
	private boolean open = false;
	@FXML 
	private ImageView downArrow;
	@FXML 
	private VBox InstructionVBox;
	@FXML 
	private JFXButton eatenBtn;
	@FXML 
	private VBox ingredientVBox;
	
	@FXML
	public void initialize() throws IOException{
		String meal = FoodCalculatorCaloriesCalculatorRecipeModel.getMeal();
		int calories = FoodCalculatorCaloriesCalculatorRecipeModel.getTargetCalories();
		FoodCalculatorCaloriesCalculatorRecipeModel i = new FoodCalculatorCaloriesCalculatorRecipeModel(meal, calories);
		//First Box
		Time.setText(meal);
		NumberOfCalories.setText(i.getRecipeWithinCalories().getCalories() + " Calories");
		Measurements.setText(i.getRecipeWithinCalories().getServing() + " Serving");
		FoodName.setText(i.getRecipeWithinCalories().getName());
		FoodImage.setImage(new Image(i.getRecipeWithinCalories().getPicture()));
		//Second Box
		for(Ingredient a:i.getRecipeWithinCalories().getIngredient()){
			ShoppingListDAO dao = new ShoppingListDAO();
			ImageView iv;
			try{
				dao.getStockModelByName(a.getName()).equals(null);
				iv = new ImageView(new Image("/theFridge/picture/checked.png"));
			}catch(NullPointerException e){
				iv = new ImageView(new Image("/theFridge/picture/cancel.png"));
			}
			System.out.println(a.getAmount());
			String line = a.getAmount() + " " + a.getMeasurement() + " " + a.getName();
			Label lbl = new Label(line);
			lbl.setFont(new Font(18));
			lbl.setStyle("-fx-padding: 10 0 10 10");
			iv.setFitWidth(30);
			iv.setFitHeight(30);
			iv.setStyle("-fx-padding: 10 0 0 10");
			HBox hbox = new HBox(iv, lbl);
			ingredientVBox.getChildren().add(hbox);
		}
		TextArea txta = new TextArea(FoodCalculatorCaloriesCalculatorRecipeInstructionModel.printInstruction(i.getRecipeWithinCalories().getName()));
		txta.setEditable(false);
		txta.setWrapText(true);
		InstructionVBox.getChildren().addAll(txta);
		
		//Show Profile Picture on Circle
		Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
		//--------------------------------
	}
	
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/LoginSignupPage.fxml"));
			stage.setMaximized(false);
		}
		stage.setScene(new Scene(root));
 	    stage.show();
	}
	
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
			root = FXMLLoader.load(getClass().getResource("/theFridge/view/RecipePage.fxml"));
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

	@FXML
	public void showInstructions(MouseEvent event) {
		
	}
}
