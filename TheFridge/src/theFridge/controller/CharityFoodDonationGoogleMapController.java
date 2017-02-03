package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import theFridge.model.CharityFoodDonationGoogleMapModel;
import theFridge.model.User;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

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
	@FXML TextArea moreInfoInfo2;
	@FXML ImageView moreInfoPic;
	@FXML TextField moreInfoLocation;
	@FXML TextArea moreInfoInfo1;
	@FXML Text moreInfoTitle;
	@FXML TextFlow closeMoreInfoBtn;
	@FXML AnchorPane moreInfo;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        try {
        	User u = new User();
			u = u.getCurrentUser();
			Image img = new Image(u.getProfileImage());
			profileCircle.setFill(new ImagePattern(img));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }     
	
    @FXML 
    public void showDonatePage(ActionEvent event) throws IOException {
    	double lat = results.getRoutes().get(0).getLegs().get(0).getEndLocation().getLatitude();
    	if(lat == 1.3375365){
    		System.out.println("Food From The Heart");
    		CharityFoodDonationGoogleMapModel.OrganizationTxt = "Food From The Heart";
    		DonationSubmitConfirmPopupPageController.timeTaken = 17;
    	}
    	else if(lat == 1.3176681){
    		System.out.println("Willing Hearts");
    		CharityFoodDonationGoogleMapModel.OrganizationTxt = "Willing Hearts";
    		DonationSubmitConfirmPopupPageController.timeTaken = 18;
    	}
    	else if(lat == 1.2723219){
			System.out.println("Food Bank WareHouse");
			CharityFoodDonationGoogleMapModel.OrganizationTxt = "Food Bank Singapore";
			DonationSubmitConfirmPopupPageController.timeTaken = 21;
		}
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
 		Parent root =  FXMLLoader.load(getClass().getResource("/theFridge/view/DonationPage.fxml"));
    	stage.setScene(new Scene(root));
    	stage.show();
	}

	@FXML 
	public void showMoreInfo(ActionEvent event) {
		double lat = results.getRoutes().get(0).getLegs().get(0).getEndLocation().getLatitude();
		Timeline timeline = new Timeline();
    	KeyValue keyValue = new KeyValue(moreInfo.translateYProperty(), 0);
    	KeyFrame KeyFrame = new KeyFrame(Duration.millis(200), keyValue);
 						
		timeline.getKeyFrames().addAll(KeyFrame);
		timeline.play();
		if(lat == 1.3729919){
			System.out.println("Hougang Mall");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nHougang Mall, Level 3 (Opposite Customer Service Counter), 90 Hougang Avenue 10 Singapore 538766 \n(Operating hours: 10am to 10pm daily)");
		}
		else if(lat == 1.3375365){
			System.out.println("Food From The Heart");
		}
		else if(lat == 1.3176681){
			System.out.println("Willing Hearts");
		}
		else if(lat == 1.3016873){
			System.out.println("Parkway Parade");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nGiant Hypermarket Parkway Parade, Self-Checkout Area, 80 Marine Parade Road #03-27/30 Singapore 449269 \n(Operating hours: 9am to 10pm)");
		}
		else if(lat == 1.3545257){
			System.out.println("Tampines 1");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nTampines 1, B1 Customer Service Counter, 10 Tampines Central 1, Singapore 529536 \n(Operating hours: 10am to 10pm daily)");
		}
		else if(lat == 1.352816){
			System.out.println("Century Square");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nCentury Square, Level 2 Play & Learn Corner (near Customer Service counter), 2 Tampines Central 5 Singapore 529509 \n(Operating hours: 10am to 10pm daily)");
		}
		else if(lat == 1.3140081){
			System.out.println("100 Sims Ave");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nThe Food Pantry, 100 Sims Avenue Singapore 387426 \n(Operating hours: 10am to 10pm daily)");
		}
		else if(lat == 1.3009035){
			System.out.println("NUS Engineering");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nNational University of Singapore: \nSchool of Engineering, Techno Edge Canteen");
		}
		else if(lat == 1.2952865){
			System.out.println("NUS Arts & Social Sciences");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nNational University of Singapore: \nFaculty of Arts & Social Sciences, The Deck Canteen");
		}
		else if(lat == 1.2948112){
			System.out.println("NUS Computing");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nNational University of Singapore: \nSchool of Computing, The Terrace Canteen");
		}
		else if(lat == 1.3391538){
			System.out.println("The Grandstand");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nGiant Hypermarket Grand Stand , Entrance 200 Turf Club Road #01-1 Singapore 287994 \n(Operating hours: Sun-Thurs: 9am - 10pm, Fri-Sat: 9am - 11pm)");
		}
		else if(lat == 1.3311433){
			System.out.println("BIG BOX");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nBig Box, Level 1, Check-out exit at Supermarket, 1 Venture Ave Singapore 608521 \n(Operating hours: 10am to 10pm)");
		}
		else if(lat == 1.3118848){
			System.out.println("City Square Mall");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nCity Square Mall, Level 2 (Beside Customer Service Counter)\n180 Kitchener Road, Singapore 208539\n(Operating hours: Mon to Sun 10am to 10pm)");
		}
		else if(lat == 1.2919945){
			System.out.println("Armf II (Liang Court) Pte Ltd");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "Liang Court Shopping Mall, L2 Customer Service Counter, 177 River Valley Road, Singapore 179030 \n(Operating hours: 10am to 10pm)");
		}
		else if(lat == 1.2477918){
			System.out.println("Quayside Isle");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nQuayside Isle, Sentosa Cove: B1, outside Management Office \n(Operating hours: Mon to Sun 10am to 10pm)");
		}
		else if(lat == 1.2639856){
			System.out.println("Giant, VivoCity");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nGiant Hypermarket Vivocity B2, beside the customer service counter \n(Operating hours: Mon to Sun 10am to 10pm)");
		}
		else if(lat == 1.2655133){
			System.out.println("VivoCity");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nVivocity, Lobby N Level 3, Sentosa Concourse \n(Operating hours: Mon to Sun 10am to 10pm)");
		}
		else if(lat == 1.2888443){
			System.out.println("Chrysler Jeep Automotive of Singapore Pte Ltd");
			moreInfoInfo1.insertText(moreInfoInfo1.getLength(), "\n\nChrysler Jeep Automotive of Singapore, 1 Chang Charn Road, Singapore 159630 \n(Operating hours: 9am to 7pm daily)");
		}
		else if(lat == 1.2723219){
			System.out.println("Food Bank WareHouse");
			moreInfoInfo1.setText("Mission and Vision\nThe Food Bank Singapore is a registered charity founded by Nichol and Nicholas Ng in January 2012 and was given charity status in August 2012 (UEN: 201200654E). \nThe Food Bank Singapore strives to bridge the gap in the market by collecting surplus food in the market and providing it to organisations and people in need of food.\n\nVision: \n\nTo be the prevailing centralised coordinating organisation for all food donations and play a key role in the reduction of food wastage within the whole supply chain.\n\nMission:\n\nTo bridge potential donors and members\nTo provide access to and knowledge of cheaper sources of food for members\nTo spread the word on the importance of food resource planning to ensure long-term providence of food for everyone\nTo look at ways to reduce food wastage by giving food a new lease of life through creative and alternative ways\nTarget Donors\n\nFood companies\nRetailers\nCharities with excess donations\nHome consumers\nTarget Beneficiaries (Members)\n\nFamily service centres\nVarious types of homes\nSoup kitchens\nOther VWOs ");
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
    
    //Animation for the MoreInfo
    @FXML 
    public void closeMoreInfo(MouseEvent event) {
    	Timeline timeline = new Timeline();
    	KeyValue keyValue = new KeyValue(moreInfo.translateYProperty(), 1000);
    	KeyFrame KeyFrame = new KeyFrame(Duration.millis(200), keyValue);
 						
		timeline.getKeyFrames().addAll(KeyFrame);
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
