package theFridge.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import theFridge.DAO.ProfileDAO;
import theFridge.model.RedeemVoucherModel;
import theFridge.model.User;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;

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
	
	@FXML
	public void initialize() throws FileNotFoundException {
		User user = new User();
		user = user.getCurrentUser();
		
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
			System.out.println("RedeemVoucherPageController - IF");
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
				System.out.println("RedeemVoucherPageController - IF within IF");
				System.out.println("Current time: " + rDV.getCurrentTime());
				System.out.println("End time: " + user.getEndTime());
				
				userPointsLabel.setOpacity(0);
				totalPoints.setOpacity(0);
				redeemAgainLabel.setOpacity(1);
				redeemAgainDate.setOpacity(1);
				
				rDV.clearRedeemAgainDate();
				rDV.unDisableVoucher(redeemVBox);
			}
		}
	}
	
	@FXML
	public void showPopup(MouseEvent event) throws FileNotFoundException {
		if (event.getSource().equals(voucher1)) {
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(voucher2)) {
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(voucher3)) {
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.show();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (event.getSource().equals(voucher4)) {
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
					Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPopup.fxml"));
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.setX(320);
					stage.setY(430);
					stage.setScene(scene);
					stage.show();
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
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("NTUC");
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("Cold Storage");
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("Sheng Siong");
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
			File file=new File("src/theFridge/file/confirm.txt");
			Scanner sc = new Scanner(file) ;
			String n = sc.nextLine();
			sc.close();
			
			ProfileDAO profileDAO = new ProfileDAO();
			User uu = new User();
			uu = profileDAO.getUser(n);
			
			RedeemVoucherModel rDV = new RedeemVoucherModel();
			RedeemVoucherModel.setVoucherType("Giant");
			
			try {
				if (uu.getTotalPoints() < rDV.getVoucherPoints()) {
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
