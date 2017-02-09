package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import theFridge.model.CharityFoodDonationGoogleMapModel;
import theFridge.model.DonationPageModel;
import theFridge.model.ListModel;
import theFridge.model.User;

public class DonationPageController {
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
	private Text OrganizationTxt;
	@FXML
	private JFXRadioButton ManualEnterRadio;
	@FXML
	private ToggleGroup food;
	@FXML
	private JFXRadioButton AutomaticRadio;
	@FXML
	private Text timeLbl;
	@FXML
	private Text operationLbl;
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
	@FXML 
	private JFXButton submitBtn;
	@FXML 
	private JFXButton resetBtn;
	@FXML
	private JFXTextField nameField;
	@FXML
	private JFXTextField emailField;
	@FXML
	private JFXTextField contactField;
	@FXML
	private VBox enterFoodVBox;
	public static boolean first = true;
	public static String OrganizationTxt1;

	@FXML
	public void initialize() throws FileNotFoundException{
		DonationPageModel.enterFoodVBox = enterFoodVBox;
		//Show profile image
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
		
		//Get user and Replace to correct Organization name and Operation Timings
		nameField.setText(user.getName());
		emailField.setText(user.getEmail());
		String organizationName = OrganizationTxt1;
		if(organizationName != null && organizationName != ""){
			OrganizationTxt.setText(organizationName);
			if(organizationName.equalsIgnoreCase("Food From The Heart")){
				operationLbl.setText("Operation Hours:\n9am to 6pm\nMonday to Friday(Excluding Public Holidays)");
			}
			else if(organizationName.equalsIgnoreCase("Willing Hearts")){
				operationLbl.setText("Operation Hours:\n6am to 4pm\nMonday to Sunday");
			}
			else if(organizationName.equalsIgnoreCase("Food Bank Singapore")){
				operationLbl.setText("Operation Hours:\nMonday to Friday - 10am to 7pm\nSaturday - 10am to 1pm");
			}
		}
		//Checking of Operation Hours and Days
		
		//---------------------------
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			@Override
			public void run(){
				Date date = new Date();
				DonationPageModel current = null;
				try {
					current = DonationPageModel.getThisModel(OrganizationTxt1);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date); 
				if(calendar.get(Calendar.DAY_OF_WEEK) >= current.getOpeningDay() && calendar.get(Calendar.DAY_OF_WEEK) <= current.getClosingDay()){
					if(calendar.get(Calendar.HOUR_OF_DAY) >= current.getOpeningHours() && calendar.get(Calendar.HOUR_OF_DAY) < current.getClosingHours()){
						timeLbl.setFill(Color.GREEN);
						timeLbl.setText(dateFormat.format(date) + " (OPEN)");
						submitBtn.setDisable(false);
					}
					else{
						timeLbl.setFill(Color.RED);
						timeLbl.setText(dateFormat.format(date) + " (CLOSED)");
						submitBtn.setDisable(true);
					}
				}
				else{
					if(calendar.get(Calendar.DAY_OF_WEEK) == current.getSecondaryOpeningDay()){
						if(calendar.get(Calendar.HOUR_OF_DAY) >= current.getSecondaryOpeningHours() && calendar.get(Calendar.HOUR_OF_DAY) < current.getSecondaryClosingHours()){
							timeLbl.setFill(Color.GREEN);
							timeLbl.setText(dateFormat.format(date) + " (OPEN)");
							submitBtn.setDisable(false);
						}
						else{
							timeLbl.setFill(Color.RED);
							timeLbl.setText(dateFormat.format(date) + " (CLOSED)");
							submitBtn.setDisable(true);
						}
					}
					else{
						timeLbl.setFill(Color.RED);
						timeLbl.setText(dateFormat.format(date) + " (CLOSED)");
						submitBtn.setDisable(true);
					}
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 1000);
	}
	
	@FXML 
	public void openPopup(MouseEvent event) throws IOException {
		Dialog<?> dialog = new Dialog<Object>();
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/ManualEnterPage.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML 
	public void showFood(ActionEvent event) throws FileNotFoundException {
		if(enterFoodVBox.getChildren().size() == 0){
			Label q = new Label("Name");
			q.setPrefWidth(400);
			Label w = new Label("Amount");
			w.setPrefWidth(200);
			HBox title = new HBox(q, w);
			title.setAlignment(Pos.CENTER);
			enterFoodVBox.getChildren().add(0, title);
		}
		for(ListModel a:DonationPageModel.getAutomaticFood()){
			Label name = new Label(a.getName());
			name.setPrefWidth(400);
			Label amount = new Label(String.valueOf((int)a.getAmount()));
			amount.setPrefWidth(200);
			HBox hbox = new HBox(name, amount);
			hbox.setAlignment(Pos.CENTER);
			hbox.setPadding(new Insets(10));
			hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					enterFoodVBox.getChildren().remove(hbox);
				}
			});
			enterFoodVBox.getChildren().add(hbox);
		}
		first = false;
	}

	@FXML 
	public void cancelDonation(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/CharityFoodDonationPage.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
		first = true;
	}
	
	@FXML 
	public void clearAllFields(ActionEvent event) {
		nameField.clear();
		emailField.clear();
		contactField.clear();
		ManualEnterRadio.setSelected(false);
		AutomaticRadio.setSelected(false);
		enterFoodVBox.getChildren().clear();
		first = true;
	}
	
	@FXML 
	public void submitDonation(ActionEvent event) throws IOException {
		DonationSubmitConfirmPopupPageController.stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		DonationSubmitConfirmPopupPageController.name = nameField.getText();
		if(!nameField.getText().isEmpty() && !nameField.getText().equals(null)){
			DonationSubmitConfirmPopupPageController.email = emailField.getText();
			if(!emailField.getText().isEmpty() && !emailField.getText().equals(null)){
				DonationSubmitConfirmPopupPageController.contact = contactField.getText();
				if(!contactField.getText().isEmpty() && !contactField.getText().equals(null)){
					CharityFoodDonationGoogleMapModel a = new CharityFoodDonationGoogleMapModel();
					DonationSubmitConfirmPopupPageController.location = a.getDestination(OrganizationTxt1);
					if(enterFoodVBox.getChildren().size() > 1){
						try{
							DonationSubmitConfirmPopupPageController.allm.clear();
							for(int i = 1; i < enterFoodVBox.getChildren().size(); i++){
								HBox hbox = (HBox) enterFoodVBox.getChildren().get(i);
								Label name = (Label) hbox.getChildren().get(0);
								Label amount = (Label) hbox.getChildren().get(1);
								ListModel lm = new ListModel(name.getText(), Double.parseDouble(amount.getText()));
								DonationSubmitConfirmPopupPageController.allm.add(lm);
							}
							DonationSubmitConfirmPopupPageController.time = timeLbl.getText().substring(0, 19);
							Dialog<?> dialog = new Dialog<Object>();
							Stage stage = (Stage) (dialog.getDialogPane()).getScene().getWindow();
							stage.initStyle(StageStyle.TRANSPARENT);
							Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/DonationSubmitConfirmPopupPage.fxml"));
							Scene scene = new Scene(root);
							scene.setFill(null);
							stage.setScene(scene);
							stage.show();
							first = true;
						}catch(Exception e){
							System.out.println("Error");
						}
					}else{
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning Dialog");
						alert.setHeaderText("No Food Items Entered");
						alert.setContentText("Please enter the food to donate using the either 2 radio buttons");

						alert.showAndWait();
					}
				}else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning Dialog");
					alert.setHeaderText("Unfilled field");
					alert.setContentText("Please enter your contact number");

					alert.showAndWait();
				}
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning Dialog");
				alert.setHeaderText("Unfilled field");
				alert.setContentText("Please enter your email");

				alert.showAndWait();
			}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Unfilled field");
			alert.setContentText("Please enter your name");

			alert.showAndWait();
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
	
	//
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
