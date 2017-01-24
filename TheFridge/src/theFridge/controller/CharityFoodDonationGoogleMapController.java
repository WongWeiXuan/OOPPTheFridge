package theFridge.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private TextField addressTextField;
    
    private GoogleMap map;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        Image img = new Image("theFridge/picture/Profile Image.jpg");
		profileCircle.setFill(new ImagePattern(img));
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
    
     @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();
        
       //mapOptions.center(new LatLong(1.374867, 103.883622))
        mapOptions.center(new LatLong(1.379256, 103.849670))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .streetViewControl(false)
                .rotateControl(true)
                .scaleControl(true)
                .zoomControl(true)
                .panControl(true)
                .mapMarker(true)
                .zoom(18);

        map = mapView.createMap(mapOptions);
        
        LatLong currentLocation = new LatLong(1.379256, 103.849670);
        LatLong xuanZhengLocation = new LatLong(1.374867, 103.883622);
        LatLong foodFromTheHeartLocation = new LatLong(1.337480, 103.882905);
        LatLong willingHeartsLocation = new LatLong(1.317637, 103.900296);
        LatLong foodBin1Location = new LatLong(1.301408, 103.905295); //80 Marine Parade Road
        LatLong foodBin2Location = new LatLong(1.354205, 103.945022); //10 Tampines Central 1
        LatLong foodBin3Location = new LatLong(1.352739, 103.943836); //2 Tampines Central 5
        LatLong foodBin4Location = new LatLong(1.313859, 103.877551); //100 Sims Avenue
        LatLong foodBin5Location = new LatLong(1.372469, 103.893780); //90 Hougang Avenue
        LatLong foodBin6Location = new LatLong(1.300259, 103.770791); //National University of Singapore: School of Engineering
        LatLong foodBin7Location = new LatLong(1.294204, 103.771121); //National University of Singapore: Faculty of Arts & Social Sciences
        LatLong foodBin8Location = new LatLong(1.295048, 103.773841); //National University of Singapore: School of Computing
        LatLong foodBin9Location = new LatLong(1.337892, 103.793338); //Entrance 200 Turf Club Road
        LatLong foodBin10Location = new LatLong(1.331880, 103.744881); //1 Venture Ave Singapore 608521
        LatLong foodBin11Location = new LatLong(1.311387, 103.856609); //180 Kitchener Road
        LatLong foodBin12Location = new LatLong(1.291532, 103.844609); //177 River Valley Road
        LatLong foodBin13Location = new LatLong(1.247633, 103.842170); //Quayside Isle, Sentosa Cove: B1
        LatLong foodBin14Location = new LatLong(1.264699, 103.823083); //Giant Hypermarket Vivocity B2
        LatLong foodBin15Location = new LatLong(1.264241, 103.822327); //Vivocity, Lobby N Level 3, Sentosa Concourse 
        LatLong foodBin16Location = new LatLong(1.288735, 103.812196); //1 Chang Charn Road
        
        
        MarkerOptions markerOptions1 = new MarkerOptions()
        		.position(currentLocation)
        		.animation(Animation.BOUNCE);
        
        MarkerOptions markerOptions2 = new MarkerOptions()
        		.position(xuanZhengLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions3 = new MarkerOptions()
        		.position(foodFromTheHeartLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions4 = new MarkerOptions()
        		.position(willingHeartsLocation)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions5 = new MarkerOptions()
        		.position(foodBin1Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions6 = new MarkerOptions()
        		.position(foodBin2Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions7 = new MarkerOptions()
        		.position(foodBin3Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions8 = new MarkerOptions()
        		.position(foodBin4Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions9 = new MarkerOptions()
        		.position(foodBin5Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions10 = new MarkerOptions()
        		.position(foodBin6Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions11 = new MarkerOptions()
        		.position(foodBin7Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions12 = new MarkerOptions()
        		.position(foodBin8Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions13 = new MarkerOptions()
        		.position(foodBin9Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions14 = new MarkerOptions()
        		.position(foodBin10Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions15 = new MarkerOptions()
        		.position(foodBin11Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions16 = new MarkerOptions()
        		.position(foodBin12Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions17 = new MarkerOptions()
        		.position(foodBin13Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions18 = new MarkerOptions()
        		.position(foodBin14Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions19 = new MarkerOptions()
        		.position(foodBin15Location)
        		.animation(Animation.DROP);
        
        MarkerOptions markerOptions20 = new MarkerOptions()
        		.position(foodBin16Location)
        		.animation(Animation.DROP);
        
        Marker currentMarker = new Marker(markerOptions1);
        Marker xuanZhengMarker = new Marker(markerOptions2);
        Marker foodFromTheHeartMarker = new Marker(markerOptions3);
        Marker willingHeartsMarker= new Marker(markerOptions4);
        Marker foodBin1Marker = new Marker(markerOptions5);
        Marker foodBin2Marker = new Marker(markerOptions6);
        Marker foodBin3Marker = new Marker(markerOptions7);
        Marker foodBin4Marker = new Marker(markerOptions8);
        Marker foodBin5Marker = new Marker(markerOptions9);
        Marker foodBin6Marker = new Marker(markerOptions10);
        Marker foodBin7Marker = new Marker(markerOptions11);
        Marker foodBin8Marker = new Marker(markerOptions12);
        Marker foodBin9Marker = new Marker(markerOptions13);
        Marker foodBin10Marker = new Marker(markerOptions14);
        Marker foodBin11Marker = new Marker(markerOptions15);
        Marker foodBin12Marker = new Marker(markerOptions16);
        Marker foodBin13Marker = new Marker(markerOptions17);
        Marker foodBin14Marker = new Marker(markerOptions18);
        Marker foodBin15Marker = new Marker(markerOptions19);
        Marker foodBin16Marker = new Marker(markerOptions20);
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h1>Current Location</h1>");

        InfoWindow currenteInfoWindow = new InfoWindow(infoWindowOptions);
        currenteInfoWindow.open(map, currentMarker);
        
        map.addMarker(currentMarker);
        map.addMarker(xuanZhengMarker);
        map.addMarker(foodFromTheHeartMarker);
        map.addMarker(willingHeartsMarker);
        map.addMarker(foodBin1Marker);
        map.addMarker(foodBin2Marker);
        map.addMarker(foodBin3Marker);
        map.addMarker(foodBin4Marker);
        map.addMarker(foodBin5Marker);
        map.addMarker(foodBin6Marker);
        map.addMarker(foodBin7Marker);
        map.addMarker(foodBin8Marker);
        map.addMarker(foodBin9Marker);
        map.addMarker(foodBin10Marker);
        map.addMarker(foodBin11Marker);
        map.addMarker(foodBin12Marker);
        map.addMarker(foodBin13Marker);
        map.addMarker(foodBin14Marker);
        map.addMarker(foodBin15Marker);
        map.addMarker(foodBin16Marker);
        
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
