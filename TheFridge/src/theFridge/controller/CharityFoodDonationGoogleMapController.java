package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.CharityFoodDonationGoogleMapModel;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;

public class CharityFoodDonationGoogleMapController implements Initializable, MapComponentInitializedListener {
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
	//For profile dropdown(Profile dropdown)
	private boolean open = false;
	
	@FXML 
	private VBox ProfileMenu;
	@FXML 
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	
	@FXML
    private GoogleMapView mapView;
	@FXML
    private Text PlaceNameLbl;
    @FXML
    private Text EtaLbl;
    @FXML
    private Text DistanceLbl;
    @FXML 
	private VBox vboxInScroll;
	@FXML 
	private JFXButton donateBtn;
	@FXML 
	private JFXButton moreInfoBtn;
	@FXML 
	private VBox donationVbox;
	public static DirectionsResult results;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
    }     
	
    @FXML 
    public void showDonatePage(ActionEvent event) throws IOException {
    	double lat = results.getRoutes().get(0).getLegs().get(0).getEndLocation().getLatitude();
    	if(lat == 1.3375365){
    		System.out.println("Food From The Heart");
    	}
    	else if(lat == 1.3176681){
    		System.out.println("Willing Hearts");
    	}
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
 		Parent root =  FXMLLoader.load(getClass().getResource("/theFridge/view/DonationPage.fxml"));
    	stage.setScene(new Scene(root));
    	stage.show();
	}

	@FXML 
	public void showMoreInfo(ActionEvent event) {
		double lat = results.getRoutes().get(0).getLegs().get(0).getEndLocation().getLatitude();
		if(lat == 1.3729919){
			System.out.println("Hougang Mall");
		}
		else if(lat == 1.3375365){
			System.out.println("Food From The Heart");
		}
		else if(lat == 1.3176681){
			System.out.println("Willing Hearts");
		}
		else if(lat == 1.3016873){
			System.out.println("Parkway Parade");
		}
		else if(lat == 1.3545257){
			System.out.println("Tampines 1");
		}
		else if(lat == 1.352816){
			System.out.println("Century Square");
		}
		else if(lat == 1.3140081){
			System.out.println("100 Sims Ave");
		}
		else if(lat == 1.3009035){
			System.out.println("NUS Engineering");
		}
		else if(lat == 1.2952865){
			System.out.println("NUS Arts & Social Sciences");
		}
		else if(lat == 1.2948112){
			System.out.println("NUS Computing");
		}
		else if(lat == 1.3391538){
			System.out.println("The Grandstand");
		}
		else if(lat == 1.3311433){
			System.out.println("BIG BOX");
		}
		else if(lat == 1.3118848){
			System.out.println("City Square Mall");
		}
		else if(lat == 1.2919945){
			System.out.println("Armf II (Liang Court) Pte Ltd");
		}
		else if(lat == 1.2477918){
			System.out.println("Quayside Isle");
		}
		else if(lat == 1.2639856){
			System.out.println("Giant, VivoCity");
		}
		else if(lat == 1.2655133){
			System.out.println("VivoCity");
		}
		else if(lat == 1.2888443){
			System.out.println("Chrysler Jeep Automotive of Singapore Pte Ltd");
		}
	}
    
    @Override
    public void mapInitialized() {
    	mapView.addMapInializedListener(this);
		CharityFoodDonationGoogleMapModel model = new CharityFoodDonationGoogleMapModel(mapView, vboxInScroll, donationVbox, donateBtn, moreInfoBtn);
    	
        try {
			model.initializeMap();
		} catch (FileNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
