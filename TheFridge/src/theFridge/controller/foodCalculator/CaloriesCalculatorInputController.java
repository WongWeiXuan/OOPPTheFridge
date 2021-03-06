package theFridge.controller.foodCalculator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.CaloriesCalculatorInputModel;
import theFridge.model.FoodCalculatorCaloriesCalculatorRecipeModel;
import theFridge.model.User;

public class CaloriesCalculatorInputController {

    @FXML
    private JFXTextField calories;
    @FXML
    public Spinner<Integer> meals;
    @FXML
    private JFXButton goNext;
    @FXML
   	private Circle profileCircle;
   	@FXML 
   	private StackPane dropdownMenu;
   	@FXML 
   	private VBox dropdownWord;
   	@FXML 
   	private VBox dropdownBackground;
   	//For profile dropdown(Profile dropdown)
   	private boolean open = false;
   	
   	@FXML 
   	private VBox ProfileMenu;
   	@FXML 
   	private VBox SettingMenu;
   	@FXML
   	private VBox LogoutMenu;
	@FXML 
	private AnchorPane Anchor;
	@FXML 
	private VBox naviPreview;
	@FXML 
	private VBox homeScene;
	@FXML
	private VBox navi;
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
	private Circle Breakfast;
	@FXML
	private Circle Lunch;
	@FXML
	private Circle Dinner;
	@FXML
	private Circle Snacks;
	@FXML
	private Text infoLbl;
	
    public String NumOfMeals = "breakfast";
    
    @FXML
    void initialize() throws FileNotFoundException{
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
	        
		CaloriesCalculatorInputModel ccim = new CaloriesCalculatorInputModel();
		calories.setText(String.valueOf(ccim.getBMR()));
		Tooltip toolTip = new Tooltip("For men: \nBMR = 10 x weight (kg) + 6.25 x height (cm) � 5 x age (years) + 5\nFor women: \nBMR = 10 x weight (kg) + 6.25 x height (cm) � 5 x age (years) � 161");
		hackTooltipStartTiming(toolTip);
		Tooltip.install(infoLbl, toolTip);
    }
    
    public static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(150)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void setMeal(MouseEvent event) throws IOException{
    	if(event.getSource().equals(Breakfast)){
    		NumOfMeals = "Breakfast";
    	}
    	else if(event.getSource().equals(Lunch)){
    		NumOfMeals = "Lunch";
    	}
    	else if(event.getSource().equals(Dinner)){
    		NumOfMeals = "Dinner";
    	}
    	else if(event.getSource().equals(Snacks)){
    		NumOfMeals = "Snacks";
    	}	
    }
    
    @FXML
    void goNext(ActionEvent event) throws IOException {
    	String caloriesText = calories.getText();
    	int NumberOfCalories;
    	try{
    		NumberOfCalories = Integer.parseInt(caloriesText);
    	}catch(Exception e){
    		double NumberOfCalories1 = Double.parseDouble(caloriesText);
    		NumberOfCalories = (int)NumberOfCalories1;
    	}
    	
    	FoodCalculatorCaloriesCalculatorRecipeModel.setMeal(NumOfMeals);
    	FoodCalculatorCaloriesCalculatorRecipeModel.setTargetCalories(NumberOfCalories);
    	
    	/*
    	if(caloriesText != null || caloriesText != ""){
    		try{
    			if(Integer.parseInt(caloriesText) < 0){
        			System.out.print("Enter number above 0 please... Surprisingly this is for me to see");
        		}
        		else if(Integer.parseInt(caloriesText) >= 0){
        			NumOfCalories = Integer.parseInt(caloriesText);
        			//Change scene
        	    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculator.fxml"));
        			
        			stage.setScene(new Scene(root));
        			stage.show();
        		}
    		}
    		catch(Exception e){
    			System.out.println("Enter number please... Surprisingly this is for me to see");
    			System.out.println(e);
    		}
    	}
    	*/
		//Change scene
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/foodCalculator/CaloriesCalculator.fxml"));
		
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
	public void showNavigation(MouseEvent event){
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void hideNavigation(MouseEvent event){
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
		
 		stage.setScene(new Scene(root));
 	    stage.show();
	}
}