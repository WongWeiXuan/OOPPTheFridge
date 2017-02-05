package theFridge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import theFridge.model.RedeemVoucherModel;
import theFridge.model.User;

public class RedeemVoucherPageController {
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
	private Circle profileCircle;
	@FXML 
	private StackPane dropdownMenu;
	@FXML 
	private VBox dropdownWord;
	@FXML 
	private VBox dropdownBackground;
	@FXML 
	private VBox ProfileMenu;
	@FXML 
	private VBox SettingMenu;
	@FXML
	private VBox LogoutMenu;
	@FXML 
	private JFXButton TwelveCharacterBtn1;
	@FXML 
	private JFXButton TwelveCharacterBtn2;
	@FXML 
	private JFXButton TwelveCharacterBtn3;
	@FXML 
	private JFXButton TwelveCharacterBtn4;
	@FXML
	private Label userPointsLabel;
	@FXML
	private Label totalPoints;
	@FXML
	private Label redeemAgainLabel;
	@FXML
	private Label redeemAgainDate;
	@FXML
	private VBox voucher1;
	@FXML
	private VBox voucher2;
	@FXML
	private VBox voucher3;
	@FXML
	private VBox voucher4;
	@FXML
	private VBox main1;
	@FXML
	private VBox main2;
	@FXML
	private VBox main3;
	@FXML
	private VBox main4;
	@FXML
	private VBox show1;
	@FXML
	private VBox show2;
	@FXML
	private VBox show3;
	@FXML
	private VBox show4;
	@FXML
	private VBox redeemVBox;
	@FXML
	private ImageView mailImg1;
	@FXML
	private ImageView mailImg2;
	@FXML
	private ImageView mailImg3;
	@FXML
	private ImageView mailImg4;
	
	//For profile dropdown(Profile dropdown)
	private boolean open = false;
	
	@FXML
	public void initialize() throws FileNotFoundException {
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
		
		RedeemVoucherModel rDV = new RedeemVoucherModel();
		String points = Integer.toString(user.getTotalPoints());
		
		//Show user's total points if they haven't redeemed any promo code
		totalPoints.setText(points);
		userPointsLabel.setOpacity(1);
		totalPoints.setOpacity(1);
		redeemAgainLabel.setOpacity(0);
		redeemAgainDate.setOpacity(0);
		
		//Show the date user can redeem again if endTime is not 0
		if (user.getEndTime() != 0) {
			System.out.println("Promo code: " + user.getPromoCode());
			System.out.println("End time: " + user.getEndTime());
			
			totalPoints.setOpacity(0);
			userPointsLabel.setOpacity(0);
			redeemAgainLabel.setOpacity(1);
			
			long endTime = user.getEndTime();
			Timer timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							long remainingTime = endTime - System.currentTimeMillis();
							redeemAgainDate.setText(rDV.changeToTimeFormat(remainingTime));
						}
					});
				}
			};
			timer.scheduleAtFixedRate(timerTask, 0, 1000);
			
			//redeemAgainDate.setText(rDV.changeToDateFormat(user.getEndTime()));
			redeemAgainDate.setOpacity(1);
			
			rDV.disableVoucher(redeemVBox);
			mailImg1.setOpacity(0.3);
			mailImg2.setOpacity(0.3);
			mailImg3.setOpacity(0.3);
			mailImg4.setOpacity(0.3);
			
			//Allow user to redeem again if currentTime reaches the endTime
			if (rDV.getCurrentTime() >= user.getEndTime()) {
				System.out.println("Current time: " + rDV.getCurrentTime());
				System.out.println("End time: " + user.getEndTime());
				
				totalPoints.setText(points);
				userPointsLabel.setOpacity(1);
				totalPoints.setOpacity(1);
				redeemAgainLabel.setOpacity(0);
				redeemAgainDate.setOpacity(0);
				
				rDV.clearRedeemAgainDate();
				rDV.unDisableVoucher(redeemVBox);
			}
		}
	}
	
	@FXML
	public void showPopup(MouseEvent event) throws FileNotFoundException {
		RedeemConfirmPopupController.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		if (event.getSource().equals(voucher1)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(voucher2)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(voucher3)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(voucher4)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void show(MouseEvent event) {
		if (event.getSource().equals(voucher1)) {
			main1.setVisible(false);
			show1.setVisible(true);
		}
		else if (event.getSource().equals(voucher2)) {
			main2.setVisible(false);
			show2.setVisible(true);
		}
		else if (event.getSource().equals(voucher3)) {
			main3.setVisible(false);
			show3.setVisible(true);
		}
		else if (event.getSource().equals(voucher4)) {
			main4.setVisible(false);
			show4.setVisible(true);
		}
	}
	
	@FXML
	public void hide(MouseEvent event) {
		if (event.getSource().equals(voucher1)) {
			main1.setVisible(true);
			show1.setVisible(false);
		}
		else if (event.getSource().equals(voucher2)) {
			main2.setVisible(true);
			show2.setVisible(false);
		}
		else if (event.getSource().equals(voucher3)) {
			main3.setVisible(true);
			show3.setVisible(false);
		}
		else if (event.getSource().equals(voucher4)) {
			main4.setVisible(true);
			show4.setVisible(false);
		}
	}

	@FXML 
	public void goToPromoPage(ActionEvent event) throws IOException {
		if (event.getSource().equals(TwelveCharacterBtn1)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("NTUC");
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucher12CharacterPage.fxml"));
					stage.setScene(new Scene(root));
			 	    stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(TwelveCharacterBtn2)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("Cold Storage");
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucher12CharacterPage.fxml"));
					stage.setScene(new Scene(root));
			 	    stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(TwelveCharacterBtn3)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("Sheng Siong");
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucher12CharacterPage.fxml"));
					stage.setScene(new Scene(root));
			 	    stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(TwelveCharacterBtn4)) {
			User user = new User();
			user = user.getCurrentUser();
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("Giant");
			
			try {
				if (user.getTotalPoints() < rDV.getVoucherPoints()) {
					@SuppressWarnings("rawtypes")
					Dialog dialog = new Dialog();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemInsufficientPointsPopup.fxml"));
					Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.showAndWait();
				}
				else {
					Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucher12CharacterPage.fxml"));
					stage.setScene(new Scene(root));
			 	    stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
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
				}
			);
						
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
				}
			);
						
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
	public void showNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), 0);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 0);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}
	
	@FXML
	public void hideNavigation(MouseEvent event) {
		Timeline timeline = new Timeline();
		KeyValue naviXValue = new KeyValue(navi.layoutXProperty(), -150);
		KeyValue naviPreviewOpacity = new KeyValue(naviPreview.opacityProperty(), 1);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(300), naviXValue, naviPreviewOpacity);
		
		timeline.getKeyFrames().addAll(keyFrame);
		timeline.play();
	}

	@FXML
	public void changeScene(MouseEvent event) throws IOException{
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
