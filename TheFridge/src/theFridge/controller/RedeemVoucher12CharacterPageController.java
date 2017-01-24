package theFridge.controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import theFridge.DAO.ProfileDAO;
import theFridge.model.RedeemVoucherModel;
import theFridge.model.User;

public class RedeemVoucher12CharacterPageController {
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
	private JFXButton codeGenerator;
	@FXML
	private JFXButton redeemBtn;
	@FXML
	private Label codeLabel;
	@FXML
	private StackPane sendPromoLabel;
	@FXML
	private Label promoCodeEmail;
	@FXML
	private Label promoCodeEmailBorder;
	@FXML
	private Label voucherTypeLabel;
	
	//private String promoCode;
	
	@FXML
	public void initialize() {
		voucherTypeLabel.setText("For a 10% off " + RedeemVoucherModel.getVoucherType() + " voucher");
	}
	
	@FXML
	public void generatePromoCode(ActionEvent event) throws IOException {
		/*RedeemVoucherModel rDV = new RedeemVoucherModel();
		rDV.generatePromoCode();
		promoCode = rDV.getCodeOutput();
		*/
		
		File file = new File("src/theFridge/file/confirm.txt");
		Scanner sc = new Scanner(file) ;
		String n = sc.nextLine();
		sc.close();
		
		ProfileDAO profileDAO = new ProfileDAO();
		
		User user = new User();
		user = profileDAO.getUser(n);
		
		//user.setPromoCode(promoCode);
		//user.addPromoCode();
		
		
		if (!(user.getPromoCode().equals("")) && !(user.getPromoCode().equals("null"))) {
			System.out.println("RedeemVoucher12CharacterPageController - IF");
			System.out.println(user.getPromoCode());
			
			//sendPromoLabel.setVisible(false);
			//codeGenerator.setDisable(false);
			
			codeLabel.setText(user.getPromoCode());
			sendPromoLabel.setVisible(true);
			codeGenerator.setDisable(true);
		}
		else {
			System.out.println("RedeemVoucher12CharacterPageController - ELSE");
			
			//codeLabel.setText(user.getPromoCode());
			//sendPromoLabel.setVisible(true);
			//codeGenerator.setDisable(true);
			
			sendPromoLabel.setVisible(false);
			codeGenerator.setDisable(false);
		}
		
		try {
			@SuppressWarnings("rawtypes")
			Dialog dialog = new Dialog();
			Parent root = FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemConfirmPromoPopup.fxml"));
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			stage.setX(320);
			stage.setY(430);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh(String code) {
		codeLabel.setText(code);
	}
	
	@FXML
	public void showPopup(MouseEvent event) {
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showBorder(MouseEvent event) {
		promoCodeEmailBorder.setVisible(true);
	}
	
	@FXML
	public void hideBorder(MouseEvent event) {
		promoCodeEmailBorder.setVisible(false);
	}
	
	@FXML
	public void goToRedeemPage(ActionEvent event) throws IOException {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/theFridge/view/RedeemVoucherPage.fxml"));
		
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